package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.ExecutedGameEventReceivedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.Contexts.Providers.IEventContextProvider;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.GameState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public final class GameEventManager extends AbstractGameManager {
    private final Set<IGameEventDefinition<? extends EventContext>> executedGameEventTypes = new HashSet<>();
    private final ApplicationEventPublisher applicationEventPublisher;

    private final List<IEventContextProvider<? extends EventContext>> eventContextProviders;

    public GameEventManager(
        GameState gameState,
        ApplicationEventPublisher applicationEventPublisher,
        List<IEventContextProvider<? extends EventContext>> eventContextProviders
    ) {
        super(gameState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.eventContextProviders = eventContextProviders;
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        for (IEventContextProvider<? extends EventContext> provider : eventContextProviders) {
            processProvider(provider);
        }
    }

    private <T extends EventContext> void processProvider(IEventContextProvider<T> provider) {
        T context = provider.buildContext();
        List<IGameEventDefinition<T>> events = provider.getEvents();

        generateEventCandidates(events, context).ifPresent(candidates -> {
            IGameEventDefinition<T> selectedEvent = selectEventFromCandidates(candidates);

            executedGameEventTypes.add(selectedEvent);
            applicationEventPublisher.publishEvent(
                new ExecutedGameEventReceivedEvent(selectedEvent.happen(candidates.context()))
            );
        });
    }

    private <T extends EventContext> Optional<EventCandidates<T>> generateEventCandidates(
        List<IGameEventDefinition<T>> events,
        T context
    ) {
        List<IGameEventDefinition<T>> eligibleEvents = new ArrayList<>();
        double totalWeight = 0.0;

        for (IGameEventDefinition<T> event : events) {
            if (executedGameEventTypes.contains(event) && event.firesOnce()) {
                continue;
            }
            if (!event.canRun(context)) {
                continue;
            }

            float weight = event.probability();
            if (weight <= 0.0f) {
                continue;
            }

            eligibleEvents.add(event);
            totalWeight += weight;
        }

        if (eligibleEvents.isEmpty() || totalWeight <= 0.0) {
            return Optional.empty();
        }

        return Optional.of(new EventCandidates<>(eligibleEvents, totalWeight, context));
    }

    private <T extends EventContext> IGameEventDefinition<T> selectEventFromCandidates(EventCandidates<T> candidates) {
        double randomValue = ThreadLocalRandom.current().nextDouble() * candidates.totalWeight();
        double accumulatedWeight = 0.0;

        for (IGameEventDefinition<T> event : candidates.candidates()) {
            accumulatedWeight += event.probability();
            if (randomValue < accumulatedWeight) {
                return event;
            }
        }

        return candidates.candidates().getLast();
    }
}

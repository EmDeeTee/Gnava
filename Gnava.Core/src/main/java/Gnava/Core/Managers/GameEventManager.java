package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.ExecutedGameEventReceivedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.Contexts.Providers.IEventContextProvider;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.TimeState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public final class GameEventManager extends AbstractGameManager {
    // TODO/NOTE: Maybe just store .class of executed event types instead of the object
    private final Set<IGameEventDefinition<? extends EventContext>> executedGameEventTypes = new HashSet<>();
    private final ApplicationEventPublisher applicationEventPublisher;

    private final List<IEventContextProvider<? extends EventContext>> eventContextProviders;

    public GameEventManager(
        TimeState timeState,
        ApplicationEventPublisher applicationEventPublisher,
        List<IEventContextProvider<? extends EventContext>> eventContextProviders
    ) {
        super(timeState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.eventContextProviders = eventContextProviders;
    }

    public boolean hasEventHappened(Class<? extends IGameEventDefinition<?>> eventType) {
        return executedGameEventTypes.stream().anyMatch(t -> t.getClass() == eventType);
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        // TODO: This would only select 1 event. But diff providers can cause more than 1 to be selected
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
            if (!event.canRun(context)) {
                continue;
            }
            if (executedGameEventTypes.contains(event) && event.firesOnce()) {
                continue;
            }
            if (!prerequisitesMet(event)) {
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

    private boolean prerequisitesMet(IGameEventDefinition<?> event) {
        return event.prerequisites().stream()
            .allMatch(required ->
                executedGameEventTypes.stream().anyMatch(executed -> executed.getClass().equals(required))
            );
    }
}

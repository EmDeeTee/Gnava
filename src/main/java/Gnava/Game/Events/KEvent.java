package Gnava.Game.Events;

import Gnava.Game.Events.Builders.GameEventBuilder;

public final class KEvent {
    private KEvent() { }

    public static GameEvent create() {
        return new GameEventBuilder()
            .withTitle("A strange figure seen on the horizon")
            .withDescription("..æ.")
            .when(KEvent::whenCondition)
            .once()
            .isStoryEvent()
            .build();
    }

    private static boolean whenCondition(EventContext ctx) {
        return ctx.getGameState().getWorldStatistics().population() > 2000 &&
               ctx.getGameState().getWorldStatistics().settlementCount() >= 2;
    }
}

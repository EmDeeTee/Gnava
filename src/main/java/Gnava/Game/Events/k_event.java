package Gnava.Game.Events;

import Gnava.Game.Events.Builders.GameEventBuilder;

public final class k_event {
    public static GameEvent kk_event() {
        return new GameEventBuilder()
            .withTitle("A strange figure seen on the horizon")
            .withDescription("..æ.")
            .when(ctx ->
                ctx.getGameState().getWorldStatistics().population() > 2000 &&
                ctx.getGameState().getWorldStatistics().settlementCount() >= 2
            )
            .once()
            .isStoryEvent()
            .build();
    }
}

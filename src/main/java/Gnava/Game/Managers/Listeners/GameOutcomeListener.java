package Gnava.Game.Managers.Listeners;

import Gnava.Game.Events.GameOutcomeReceivedEvent;

@FunctionalInterface
public interface GameOutcomeListener {
    void onGameEnded(GameOutcomeReceivedEvent gameOutcome);
}

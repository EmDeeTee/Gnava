package Gnava.Core.Managers.Listeners;

import Gnava.Core.Events.GameOutcomeReceivedEvent;

@FunctionalInterface
public interface GameOutcomeListener {
    void onGameEnded(GameOutcomeReceivedEvent gameOutcome);
}

package Gnava.Core.Events.Listeners;

import Gnava.Core.Events.GameOutcomeReceivedEvent;

@FunctionalInterface
public interface GameOutcomeListener {
    void onGameEnded(GameOutcomeReceivedEvent gameOutcome);
}

package Gnava.Core.Managers;

import Gnava.Core.TimeState;

public abstract class AbstractGameManager {
    protected final TimeState timeState;

    public AbstractGameManager(TimeState timeState) {
        this.timeState = timeState;
    }
}

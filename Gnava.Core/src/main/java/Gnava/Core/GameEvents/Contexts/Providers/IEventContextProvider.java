package Gnava.Core.GameEvents.Contexts.Providers;

import Gnava.Core.GameEvents.Contexts.EventContext;

public interface IEventContextProvider<TContext extends EventContext> {
    TContext buildContext();
}

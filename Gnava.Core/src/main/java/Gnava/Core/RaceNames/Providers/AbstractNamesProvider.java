package Gnava.Core.RaceNames.Providers;

import Gnava.Core.RaceNames.CreatureName;

public abstract class AbstractNamesProvider implements ICreatureNamesProvider {
    protected CreatureName name(String firstname, String lastname) {
        return new CreatureName(firstname, lastname, getType());
    }
}

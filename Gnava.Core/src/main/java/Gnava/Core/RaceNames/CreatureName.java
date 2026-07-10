package Gnava.Core.RaceNames;

import Gnava.Core.Models.Settlement.Enums.SettlementPopulationType;

public record CreatureName(String firstname, String lastname, SettlementPopulationType populationType) {
    public String fullName() {
        return firstname + " " + lastname;
    }

    @Override
    public String toString() {
        return fullName();
    }
}

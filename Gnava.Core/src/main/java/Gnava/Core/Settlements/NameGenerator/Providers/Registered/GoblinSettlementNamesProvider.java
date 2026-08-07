package Gnava.Core.Settlements.NameGenerator.Providers.Registered;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.Core.Settlements.NameGenerator.Providers.ISettlementNamesProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class GoblinSettlementNamesProvider implements ISettlementNamesProvider {
    @Override
    public List<String> getSettlementNames() {
        return List.of(
            "Goblinplace",
            "Cragtooth",
            "Muckmire",
            "Rustgully",
            "Gnashtown",
            "Scraphaven",
            "Bogspit",
            "Rotwood",
            "Shantyshack",
            "Grimclutch"
        );
    }

    @Override
    public SettlementPopulationType getType() {
        return SettlementPopulationType.GOBLIN;
    }
}

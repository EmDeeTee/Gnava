package Gnava.Core.Settlements.NameGenerator.Providers.Registered;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.NameGenerator.Providers.ISettlementNamesProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class GnomeSettlementNamesProvider implements ISettlementNamesProvider {
    @Override
    public List<String> getSettlementNames() {
        return List.of(
            "Gnomoria",
            "Gnomyplace",
            "Tinkerhole",
            "Clockspire",
            "Cogswallow",
            "Gearhaven",
            "Sparkfallow",
            "Copperburrow",
            "Whizglen",
            "Brambletink",
            "Shinyvale"
        );
    }

    @Override
    public SettlementPopulationType getType() {
        return SettlementPopulationType.GNOME;
    }
}

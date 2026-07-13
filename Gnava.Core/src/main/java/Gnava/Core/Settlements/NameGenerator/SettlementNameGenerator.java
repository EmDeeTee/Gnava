package Gnava.Core.Settlements.NameGenerator;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.NameGenerator.Providers.ISettlementNamesProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Service
public final class SettlementNameGenerator {
    private final List<ISettlementNamesProvider> settlementNamesProviders;

    public SettlementNameGenerator(List<ISettlementNamesProvider> settlementNamesProviders) {
        this.settlementNamesProviders = settlementNamesProviders;
    }

    public SettlementNameGenerationResult generate(SettlementPopulationType targetPopulationType) {
        ISettlementNamesProvider provider = settlementNamesProviders.stream()
            .filter(p -> p.getType() == targetPopulationType)
            .findFirst()
            // NOTE/TODO: Maybe use an Optional instead
            .orElseThrow(() -> new NoSuchElementException("Name provider empty"));

        List<String> names = provider.getSettlementNames();

        return new SettlementNameGenerationResult(
            names.get(ThreadLocalRandom.current().nextInt(0, names.size())),
            targetPopulationType
        );
    }
}

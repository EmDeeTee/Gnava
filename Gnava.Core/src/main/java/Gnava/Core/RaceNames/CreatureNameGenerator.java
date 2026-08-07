package Gnava.Core.RaceNames;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.RaceNames.Providers.ICreatureNamesProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Service
public final class CreatureNameGenerator {
    private final Map<SettlementPopulationType, ICreatureNamesProvider> creatureNamesProviders;

    public CreatureNameGenerator(List<ICreatureNamesProvider> creatureNamesProviders) {
        this.creatureNamesProviders = creatureNamesProviders.stream()
            .collect(Collectors.toMap(
                ICreatureNamesProvider::getType,
                Function.identity()
            ));
    }

    public CreatureNameGenerationResult generate(SettlementPopulationType targetPopulationType) {
        return generate(targetPopulationType, ThreadLocalRandom.current());
    }

    public CreatureNameGenerationResult generate(
        SettlementPopulationType targetPopulationType,
        RandomGenerator random
    ) {
        if (!creatureNamesProviders.containsKey(targetPopulationType)) {
            return new CreatureNameGenerationResult(
                Optional.empty(),
                targetPopulationType
            );
        }

        ICreatureNamesProvider namesProvider = creatureNamesProviders.get(targetPopulationType);
        List<CreatureName> possibleCreatureNames = namesProvider.getCreatureNames();

        if (possibleCreatureNames.isEmpty()) {
            return new CreatureNameGenerationResult(
                Optional.empty(),
                targetPopulationType
            );
        }

        return new CreatureNameGenerationResult(
            Optional.of(possibleCreatureNames.get(random.nextInt(possibleCreatureNames.size()))),
            targetPopulationType
        );
    }
}

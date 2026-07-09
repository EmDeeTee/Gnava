package Gnava.Core.RaceNames;

import Gnava.Core.Models.Enums.SettlementPopulationType;
import Gnava.Core.RaceNames.Providers.ICreatureNamesProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
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

    public CreatureName generate(SettlementPopulationType targetPopulationType) {
        if (!creatureNamesProviders.containsKey(targetPopulationType)) {
            // NOTE: This is probably not a very good idea
            return new CreatureName("Not", "Found", SettlementPopulationType.GNOME);
        }
        ICreatureNamesProvider namesProvider = creatureNamesProviders.get(targetPopulationType);
        List<CreatureName> possibleCreatureNames = namesProvider.getCreatureNames();

        return possibleCreatureNames.get(ThreadLocalRandom.current().nextInt(possibleCreatureNames.size()));
    }
}

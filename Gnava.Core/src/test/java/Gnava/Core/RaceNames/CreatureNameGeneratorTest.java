package Gnava.Core.RaceNames;

import Gnava.Core.Models.Settlement.Enums.SettlementPopulationType;
import Gnava.Core.RaceNames.Providers.ICreatureNamesProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatureNameGeneratorTest {
    private ICreatureNamesProvider gnomeProvider;
    private ICreatureNamesProvider dwarfProvider;
    private CreatureNameGenerator generator;

    @BeforeEach
    void setUp() {
        gnomeProvider = mock(ICreatureNamesProvider.class);
        dwarfProvider = mock(ICreatureNamesProvider.class);

        when(gnomeProvider.getType()).thenReturn(SettlementPopulationType.GNOME);
        when(gnomeProvider.getCreatureNames()).thenReturn(List.of(
            new CreatureName("Gnome", "Gnomington", SettlementPopulationType.GNOME)
        ));

        when(dwarfProvider.getType()).thenReturn(SettlementPopulationType.DWARF);
        when(dwarfProvider.getCreatureNames()).thenReturn(List.of(
            new CreatureName("Dwarf", "Dwarfy", SettlementPopulationType.DWARF)
        ));

        generator = new CreatureNameGenerator(List.of(gnomeProvider, dwarfProvider));
    }

    @Test
    void generate_returnsCreatureName_whenProviderExists_forGnomes() {
        CreatureName result = generator.generate(SettlementPopulationType.GNOME);

        assertNotNull(result);
        assertEquals("Gnome", result.firstname());
        assertEquals("Gnomington", result.lastname());
        assertEquals(SettlementPopulationType.GNOME, result.populationType());

        verify(gnomeProvider).getCreatureNames();
    }

    @Test
    void generate_returnsCreatureName_whenProviderExists_forDwarfs() {
        CreatureName result = generator.generate(SettlementPopulationType.DWARF);

        assertNotNull(result);
        assertEquals("Dwarf", result.firstname());
        assertEquals("Dwarfy", result.lastname());
        assertEquals(SettlementPopulationType.DWARF, result.populationType());

        verify(dwarfProvider).getCreatureNames();
    }

    // TODO: Implement this test after the name generator refactor
    @Test
    void generate_returnsFallbackName_whenProviderDoesNotExist() {
        CreatureName result = generator.generate(SettlementPopulationType.DWARF);

        assertNotNull(result);
        assertEquals("Not", result.firstname());
        assertEquals("Found", result.lastname());
        assertEquals(SettlementPopulationType.GNOME, result.populationType());

        verify(gnomeProvider, never()).getCreatureNames();
    }
}
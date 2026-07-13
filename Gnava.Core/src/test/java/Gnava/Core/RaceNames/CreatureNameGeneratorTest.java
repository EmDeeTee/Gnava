package Gnava.Core.RaceNames;

import Gnava.Core.Models.Settlement.Enums.SettlementPopulationType;
import Gnava.Core.RaceNames.Providers.ICreatureNamesProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatureNameGeneratorTest {
    private ICreatureNamesProvider gnomeProvider;
    private ICreatureNamesProvider dwarfProvider;
    private ICreatureNamesProvider goblinProvider;
    private CreatureNameGenerator generator;

    @BeforeEach
    void setUp() {
        gnomeProvider = mock(ICreatureNamesProvider.class);
        dwarfProvider = mock(ICreatureNamesProvider.class);
        goblinProvider = mock(ICreatureNamesProvider.class);

        when(gnomeProvider.getType()).thenReturn(SettlementPopulationType.GNOME);
        when(gnomeProvider.getCreatureNames()).thenReturn(List.of(
            new CreatureName("Gnome", "Gnomington")
        ));

        when(dwarfProvider.getType()).thenReturn(SettlementPopulationType.DWARF);
        when(dwarfProvider.getCreatureNames()).thenReturn(List.of(
            new CreatureName("Dwarf", "Dwarfy")
        ));

        when(goblinProvider.getType()).thenReturn(SettlementPopulationType.GOBLIN);
        when(goblinProvider.getCreatureNames()).thenReturn(Collections.emptyList());

        generator = new CreatureNameGenerator(List.of(gnomeProvider, dwarfProvider, goblinProvider));
    }

    @Test
    void generate_returnsCreatureName_whenProviderExists_forGnomes() {
        CreatureNameGenerationResult result = generator.generate(SettlementPopulationType.GNOME);

        assertNotNull(result);
        assertEquals("Gnome", result.creatureName().firstname());
        assertEquals("Gnomington", result.creatureName().lastname());
        assertTrue(result.ok());
        assertEquals(SettlementPopulationType.GNOME, result.populationType());

        verify(gnomeProvider).getCreatureNames();
    }

    @Test
    void generate_returnsCreatureName_whenProviderExists_forDwarfs() {
        CreatureNameGenerationResult result = generator.generate(SettlementPopulationType.DWARF);

        assertNotNull(result);
        assertEquals("Dwarf", result.creatureName().firstname());
        assertEquals("Dwarfy", result.creatureName().lastname());
        assertTrue(result.ok());
        assertEquals(SettlementPopulationType.DWARF, result.populationType());

        verify(dwarfProvider).getCreatureNames();
    }

    @Test
    void generate_returnsFallbackName_whenProviderDoesNotExist() {
        CreatureNameGenerationResult result = generator.generate(SettlementPopulationType.GOBLIN);

        assertNotNull(result);
        assertEquals("Provider", result.creatureName().firstname());
        assertEquals("Not found", result.creatureName().lastname());
        assertFalse(result.ok());
        assertEquals(SettlementPopulationType.GOBLIN, result.populationType());

        verify(goblinProvider).getCreatureNames();
    }
}
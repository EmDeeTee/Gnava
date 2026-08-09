package Gnava.Core.GameEvents.Contexts;

import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Core.TimeState;
import Gnava.GameApi.GameEvents.Settlements.PopulationChange;
import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;
import Gnava.GameApi.GameEvents.Settlements.SettlementWealthLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

class SettlementEventContextTest {
    private Settlement settlement;
    private SettlementEventContext context;

    @BeforeEach
    void setUp() {
        settlement = new Settlement(
            "Gnomeburg",
            50,
            100,
            SettlementPopulationType.GNOME,
            SettlementWealthLevel.MODERATE,
            false
        );
        context = new SettlementEventContext(
            mock(TimeState.class),
            mock(WorldStatisticsProvider.class),
            settlement
        );
    }

    @Test
    void settlement_returnsCurrentReadOnlySnapshot() {
        SettlementView view = context.settlement();

        assertEquals("Gnomeburg", view.name());
        assertEquals(50, view.totalPopulation());
        assertEquals(100, view.maxPopulation());
        assertEquals(50, view.populationCapacityRemaining());
        assertEquals(SettlementPopulationType.GNOME, view.populationType());
        assertEquals(SettlementWealthLevel.MODERATE, view.wealthLevel());
        assertFalse(view.player());
    }

    @Test
    void mutationOperations_updateTargetAndReportResult() {
        PopulationChange change = context.addPopulation(75);
        context.expandPopulationCapacity(20);
        context.setWealthLevel(SettlementWealthLevel.AFFLUENT);

        SettlementView updated = context.settlement();
        assertEquals(50, change.addedAmount());
        assertEquals(25, change.overflow());
        assertEquals(100, updated.totalPopulation());
        assertEquals(120, updated.maxPopulation());
        assertEquals(SettlementWealthLevel.AFFLUENT, updated.wealthLevel());
    }
}

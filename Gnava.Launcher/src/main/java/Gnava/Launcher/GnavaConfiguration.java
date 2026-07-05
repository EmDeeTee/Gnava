package Gnava.Launcher;

import Gnava.Core.GameState;
import Gnava.Core.Managers.GameEventManager;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Managers.TimeManager;
import Gnava.Core.Managers.VictoryConditionManager;
import Gnava.Core.Repositories.ISettlementRepository;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Desktop.Gnava;
import Gnava.Desktop.Interface.Frame.GameFrame;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import Gnava.Repositories.SettlementRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "Gnava")
public class GnavaConfiguration {
    @Bean
    public GameFrame gameFrame(GameState gameState, TimeManager timeManager, SettlementManager settlementManager, GameEventManager gameEventManager, VictoryConditionManager victoryConditionManager, WorldStatisticsProvider worldStatisticsProvider) {
        return new GameFrame(
            gameState,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.GKINGDOMS),
            timeManager,
            settlementManager,
            gameEventManager,
            victoryConditionManager,
            worldStatisticsProvider
        );
    }

    @Bean
    public Gnava gnava(GameState gameState, GameFrame gameFrame, SettlementManager settlementManager) {
        return new Gnava(gameState, gameFrame, settlementManager);
    }
}

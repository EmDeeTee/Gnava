package Gnava.Launcher;

import Gnava.Core.GameState;
import Gnava.Core.Repositories.ISettlementRepository;
import Gnava.Desktop.Gnava;
import Gnava.Desktop.Interface.Frame.GameFrame;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import Gnava.Repositories.SettlementRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GnavaConfiguration {
    @Bean
    public ISettlementRepository settlementRepository() {
        return new SettlementRepository();
    }

    @Bean
    public GameState gameState(ISettlementRepository repository) {
        return new GameState(repository, repository);
    }

    @Bean
    public GameFrame gameFrame(GameState gameState) {
        return new GameFrame(
            gameState,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.GKINGDOMS)
        );
    }

    @Bean
    public Gnava gnava(GameState gameState, GameFrame gameFrame) {
        return new Gnava(gameState, gameFrame);
    }
}

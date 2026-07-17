package Gnava.Launcher;

import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Managers.TimeManager;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.GameEventsPanel;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.MenuBar;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "Gnava")
public class GnavaConfiguration {
    @Bean
    public MainFrame gameFrame(
        TimeManager timeManager,
        SettlementManager settlementManager,
        MenuBar menuBar,
        GameEventsPanel gameEventsPanel
    ) {
        return new MainFrame(
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.GKINGDOMS),
            timeManager,
            settlementManager,
            menuBar,
            gameEventsPanel
        );
    }
}

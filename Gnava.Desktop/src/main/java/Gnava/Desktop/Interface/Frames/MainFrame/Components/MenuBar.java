package Gnava.Desktop.Interface.Frames.MainFrame.Components;

import Gnava.Core.Commands.CastSpellCommand;
import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Desktop.Interface.Actions.CastSpellAction;
import Gnava.Desktop.Interface.Actions.CreateSettlementAction;
import Gnava.Desktop.Interface.Actions.ShowWorldStatisticsAction;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class MenuBar extends JMenuBar {
    private final JMenu actionsMenu = new JMenu("Actions");
    private final JMenuItem createSettlementItem = new JMenuItem("Create settlement");

    private final JMenu statisticsMenu = new JMenu("Statistics");
    private final JMenuItem showWorldStatisticsItem = new JMenuItem("World statistics");

    private final JMenu spellMenu = new JMenu(TranslationManager.getInstance().getTranslationTable().t(TranslationKey.MENU_SPELL_BOOK));

    private final CastSpellCommand castSpellCommand;
    private final List<AbstractSpell> spells;

    public MenuBar(
        CreateSettlementCommand createSettlementCommand,
        CastSpellCommand castSpellCommand,
        WorldStatisticsProvider worldStatisticsProvider,
        List<AbstractSpell> spells
    ) {
        super();
        this.castSpellCommand = castSpellCommand;
        this.spells = spells;
        actionsMenu.add(createSettlementItem);
        statisticsMenu.add(showWorldStatisticsItem);

        registerSpellMenuItems();

        createSettlementItem.addActionListener(
            new CreateSettlementAction(
                createSettlementCommand,
                () -> {
                    MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
                    return new CreateSettlementPopup(frame).show().orElseThrow();
                }
            )
        );
        showWorldStatisticsItem.addActionListener(new ShowWorldStatisticsAction(worldStatisticsProvider));

        add(actionsMenu);
        add(spellMenu);
        add(statisticsMenu);
    }

    private void registerSpellMenuItems() {
        for (AbstractSpell spell : spells) {
            JMenuItem item = new JMenuItem(spell.getName());
            item.addActionListener(
                new CastSpellAction(
                    castSpellCommand,
                    () -> spell,
                    SwingUtilities.getWindowAncestor(this)
                )
            );
            spellMenu.add(item);
        }
    }
}

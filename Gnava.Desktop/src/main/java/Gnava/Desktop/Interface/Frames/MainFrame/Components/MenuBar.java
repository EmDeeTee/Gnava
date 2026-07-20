package Gnava.Desktop.Interface.Frames.MainFrame.Components;

import Gnava.Core.Commands.CastSpellCommand;
import Gnava.Core.Commands.CastSpellRequest;
import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.Managers.Settlement.SettlementCreationPolicy;
import Gnava.Core.Settlements.NameGenerator.SettlementNameGenerator;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Statistics.SpellStatisticsProvider;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Desktop.Facades.Translation;
import Gnava.Desktop.Interface.Actions.CastSpellAction;
import Gnava.Desktop.Interface.Actions.CreateSettlementAction;
import Gnava.Desktop.Interface.Actions.ShowSpellsStatisticsAction;
import Gnava.Desktop.Interface.Actions.ShowWorldStatisticsAction;
import Gnava.Desktop.Interface.Frames.ChartFrame.ChartFrame;
import Gnava.Desktop.Interface.Frames.DetailsFrame.DetailsFrame;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Desktop.Interface.Popups.Presets.SettlementSelectionPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.Translator;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class MenuBar extends JMenuBar {
    private final Translator translator;

    private final JMenu actionsMenu = new JMenu(Translation.t(TranslationKey.MENU_ACTIONS));
    private final JMenuItem createSettlementItem = new JMenuItem();

    private final JMenu statisticsMenu = new JMenu(Translation.t(TranslationKey.MENU_STATISTICS));
    private final JMenu viewMenu = new JMenu(Translation.t(TranslationKey.MENU_VIEW));

    private final JMenuItem showWorldStatisticsItem = new JMenuItem();
    private final JMenuItem showSpellStatisticsItem = new JMenuItem();
    private final JMenuItem openDetailsWindowItem = new JMenuItem();
    private final JMenuItem openChart = new JMenuItem();

    private final JMenu spellMenu = new JMenu(Translation.t(TranslationKey.MENU_SPELL_BOOK));

    private final CastSpellCommand castSpellCommand;
    private final List<AbstractSpell> spells;

    private final ISettlementProvider settlementProvider;

    public MenuBar(
        CreateSettlementCommand createSettlementCommand,
        CastSpellCommand castSpellCommand,
        WorldStatisticsProvider worldStatisticsProvider,
        SpellStatisticsProvider spellStatisticsProvider,
        List<AbstractSpell> spells,
        DetailsFrame detailsFrame,
        ChartFrame chartFrame,
        ISettlementProvider settlementProvider,
        SettlementNameGenerator settlementNameGenerator,
        Translator translator,
        SettlementCreationPolicy settlementCreationPolicy
    ) {
        super();
        this.castSpellCommand = castSpellCommand;
        this.spells = spells;
        this.settlementProvider = settlementProvider;
        this.translator = translator;
        actionsMenu.add(createSettlementItem);
        showWorldStatisticsItem.setText(translator.t("ui.menus.actions.show_world_statistics"));
        statisticsMenu.add(showWorldStatisticsItem);
        showSpellStatisticsItem.setText(translator.t("ui.menus.actions.show_spell_statistics"));
        statisticsMenu.add(showSpellStatisticsItem);
        viewMenu.add(openDetailsWindowItem);
        openChart.setText(translator.t("ui.menus.actions.show_population_chart"));
        openChart.addActionListener(l -> {
            SwingUtilities.invokeLater(() -> {
                chartFrame.setVisible(true);
                chartFrame.display();
            });
        });
        viewMenu.add(openChart);

        registerSpellMenuItems();

        createSettlementItem.setText(translator.t("ui.menus.actions.create_settlement"));
        createSettlementItem.addActionListener(l -> {
            MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            new CreateSettlementPopup(frame, settlementNameGenerator, settlementCreationPolicy).show().ifPresent(settlement -> {
                new CreateSettlementAction(
                    createSettlementCommand,
                    () -> settlement
                ).execute();
            });
        });
        showWorldStatisticsItem.addActionListener(new ShowWorldStatisticsAction(worldStatisticsProvider));
        showSpellStatisticsItem.addActionListener(new ShowSpellsStatisticsAction(spellStatisticsProvider));
        openDetailsWindowItem.setText(translator.t("ui.menus.actions.details"));
        openDetailsWindowItem.addActionListener(a -> {
            detailsFrame.setVisible(true);
        });

        add(actionsMenu);
        add(spellMenu);
        add(statisticsMenu);
        add(viewMenu);
    }

    private void registerSpellMenuItems() {
        for (AbstractSpell spell : spells) {
            JMenuItem item = new JMenuItem(spell.getName());
            item.addActionListener(a -> {
                if (spell.needsExplicitTarget()) {
                    new SettlementSelectionPopup(
                        SwingUtilities.getWindowAncestor(this),
                        settlementProvider.getAll()
                    ).show().ifPresent(settlement -> {
                        executeSpell(spell, settlement);
                    });
                } else {
                    executeSpell(spell, null);
                }
            });
            spellMenu.add(item);
        }
    }

    private void executeSpell(AbstractSpell spell, @Nullable Settlement target) {
        new CastSpellAction(
            castSpellCommand,
            () -> new CastSpellRequest(spell, target),
            SwingUtilities.getWindowAncestor(this)
        ).execute();
    }
}

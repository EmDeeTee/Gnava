package Gnava.Desktop.Interface.Frames.MainFrame;

import Gnava.Core.EventBus.Events.ExecutedGameEventReceivedEvent;
import Gnava.Core.EventBus.Events.SettlementCreatedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Events.Enums.GameOutcome;
import Gnava.Core.EventBus.Events.GameOutcomeReceivedEvent;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Managers.TimeManager;
import Gnava.Core.Settlements.Settlement;
import Gnava.Desktop.Facades.Translation;
import Gnava.Desktop.Interface.Elements.AdvanceTimeButton;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.GameEventsPanel;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.MenuBar;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.Translator;
import org.springframework.context.event.EventListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.net.URL;
import java.util.Map;

public class MainFrame extends JFrame {
    private static final Dimension PREFERRED_SIZE = new Dimension(400, 600);
    private final MenuBar menu;
    private final GameEventsPanel gameEventsPanel;

    private final DefaultListModel<Settlement> settlementListModel = new DefaultListModel<>();

    private final JList<Settlement> settlementList = new JList<>(settlementListModel);
    private final JLabel currentDayValueLabel = new JLabel("0");

    private final TimeManager timeManager;
    private final SettlementManager settlementManager;
    private final Translator translator;

    public MainFrame(
        String title,
        TimeManager timeManager,
        SettlementManager settlementManager,
        MenuBar menuBar,
        GameEventsPanel gameEventsPanel,
        Translator translator
    ) {
        super(title);
        this.menu = menuBar;
        this.timeManager = timeManager;
        this.settlementManager = settlementManager;
        this.gameEventsPanel = gameEventsPanel;
        this.translator = translator;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(PREFERRED_SIZE);
        setSize(PREFERRED_SIZE);
        setResizable(false);

        URL iconUrl = getClass().getClassLoader().getResource("icon.png");
        assert iconUrl != null;
        setIconImage(new ImageIcon(iconUrl).getImage());

        setupGui();
        registerListeners();

        pack();
        setLocationRelativeTo(null);
    }

    public void messagePlayer(String msg) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void setupGui() {
        JPanel topPanel = buildTopPanel();
        JPanel bottomPanel = buildBottomPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setJMenuBar(menu);
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        topPanel.setBackground(Color.LIGHT_GRAY);

        JLabel currentTimeLabel = new JLabel(Translation.t(TranslationKey.CURRENT_DAY));
        AdvanceTimeButton advanceTimeButton = new AdvanceTimeButton(Translation.t(TranslationKey.PASS_TIME));

        advanceTimeButton.addActionListener(e -> timeManager.advanceTime());

        topPanel.add(advanceTimeButton);
        topPanel.add(currentTimeLabel);
        topPanel.add(currentDayValueLabel);

        return topPanel;
    }

    private JPanel buildBottomPanel() {
        JScrollPane settlementScrollPane = new JScrollPane(settlementList);
        settlementScrollPane.setBorder(BorderFactory.createTitledBorder(Translation.t(TranslationKey.SETTLEMENTS)));

        JPanel bottom = new JPanel(new GridLayout(2, 1, 5, 5));
        bottom.add(gameEventsPanel);
        bottom.add(settlementScrollPane);

        return bottom;
    }

    private void registerListeners() {
        settlementList.addListSelectionListener(onSettlementSelected());
    }

    @EventListener
    private void onSettlementCreated(SettlementCreatedEvent event) {
        SwingUtilities.invokeLater(() -> {
            settlementListModel.clear();
            for (Settlement s : settlementManager.getSettlements()) {
                settlementListModel.addElement(s);
            }
        });
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        SwingUtilities.invokeLater(() -> currentDayValueLabel.setText(String.valueOf(event.currentDay())));
    }

    @EventListener
    private void onReceivedGameEvent(ExecutedGameEventReceivedEvent event) {
        SwingUtilities.invokeLater(() -> gameEventsPanel.addEvent(event.gameEvent()));
    }

    @EventListener
    private void onGameOutcomeReceived(GameOutcomeReceivedEvent gameOutcome) {
        if (gameOutcome.gameOutcome() == GameOutcome.GAME_ENDED) {
            messagePlayer("You lost");
        }
    }

    private ListSelectionListener onSettlementSelected() {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                Settlement selected = settlementList.getSelectedValue();
                if (selected != null) {
                    StringBuilder sb = new StringBuilder();

                    if (selected.isPlayer()) {
                        sb.append(translator.t("ui.popups.settlement_details.is_player"));
                    }
                    sb.append(translator.t(
                        "ui.popups.settlement_details.text",
                        Map.ofEntries(
                            Map.entry("name", selected.getName()),
                            Map.entry("population_type", selected.getPopulationType().toString()),
                            Map.entry("population", String.valueOf(selected.getTotalPopulation())),
                            Map.entry("max_population", String.valueOf(selected.getMaxPopulation())),
                            Map.entry("wealth", selected.getWealthLevel().toString())
                        )
                    ));

                    new PlaintextPopup(this, sb.toString()).show();
                    settlementList.setSelectedValue(null, false);
                }
            }
        };
    }
}

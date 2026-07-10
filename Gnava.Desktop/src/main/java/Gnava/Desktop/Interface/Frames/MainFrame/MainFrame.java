package Gnava.Desktop.Interface.Frames.MainFrame;

import Gnava.Core.EventBus.Events.ExecutedGameEventReceivedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Events.Enums.GameOutcome;
import Gnava.Core.EventBus.Events.GameOutcomeReceivedEvent;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Managers.TimeManager;
import Gnava.Core.Managers.VictoryConditionManager;
import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Desktop.Interface.Elements.AdvanceTimeButton;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.MenuBar;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import org.springframework.context.event.EventListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.net.URL;
import java.util.function.Consumer;

public class MainFrame extends JFrame {
    private static final Dimension PREFERRED_SIZE = new Dimension(400, 600);
    private final MenuBar menu;
    private final GameEventsPanel gameEventsPanel = new GameEventsPanel(this);

    private final DefaultListModel<Settlement> settlementListModel = new DefaultListModel<>();

    private final Consumer<Settlement> settlementListener = this::onSettlementsChanged;

    private final JList<Settlement> settlementList = new JList<>(settlementListModel);
    private final JLabel currentDayValueLabel = new JLabel("0");

    private final TimeManager timeManager;
    private final SettlementManager settlementManager;
    private final VictoryConditionManager victoryConditionManager;

    public MainFrame(
        String title,
        TimeManager timeManager,
        SettlementManager settlementManager,
        VictoryConditionManager victoryConditionManager,
        MenuBar menuBar
    ) {
        super(title);
        this.menu = menuBar;
        this.timeManager = timeManager;
        this.settlementManager = settlementManager;
        this.victoryConditionManager = victoryConditionManager;
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

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setJMenuBar(menu);
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        topPanel.setBackground(Color.LIGHT_GRAY);

        JLabel currentTimeLabel = new JLabel("Current day:");
        AdvanceTimeButton advanceTimeButton = new AdvanceTimeButton("Pass time");

        advanceTimeButton.addActionListener(e -> timeManager.advanceTime());

        topPanel.add(advanceTimeButton);
        topPanel.add(currentTimeLabel);
        topPanel.add(currentDayValueLabel);

        return topPanel;
    }

    private JPanel buildBottomPanel() {
        JScrollPane settlementScrollPane = new JScrollPane(settlementList);
        settlementScrollPane.setBorder(BorderFactory.createTitledBorder("Settlements"));

        JPanel bottom = new JPanel(new GridLayout(2, 1, 5, 5));
        bottom.add(gameEventsPanel);
        bottom.add(settlementScrollPane);

        return bottom;
    }

    private void registerListeners() {
        settlementManager.addSettlementCreatedListener(settlementListener);

        settlementList.addListSelectionListener(onSettlementSelected());
    }

    private void onSettlementsChanged(Settlement newSettlement) {
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
        if (gameOutcome.gameOutcome() == GameOutcome.GAME_LOST) {
            System.out.println("GAME LOST EVENT RECEIVED");
        }
    }

    private ListSelectionListener onSettlementSelected() {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                Settlement selected = settlementList.getSelectedValue();
                if (selected != null) {
                    StringBuilder sb = new StringBuilder();

                    if (selected.isPlayer()) {
                        sb.append("<b>This is your settlement!</b>").append("<br>").append("<br>");
                    }
                    sb.append("Settlement: ").append(selected.getName()).append("<br>");
                    sb.append("Population type: ").append(selected.getPopulationType()).append("<br>");
                    sb.append("Population: ").append(selected.getTotalPopulation()).append("/").append(selected.getMaxPopulation()).append("<br>");
                    sb.append("Wealth: ").append(selected.getWealthLevel()).append("<br>");

                    new PlaintextPopup(this, sb.toString()).show();
                    settlementList.setSelectedValue(null, false);
                }
            }
        };
    }
}

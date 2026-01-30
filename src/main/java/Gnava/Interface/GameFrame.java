package Gnava.Interface;

import Gnava.Game.Enums.GameOutcome;
import Gnava.Game.Events.GameOutcomeReceivedEvent;
import Gnava.Game.Events.Simulation.GameEvent;
import Gnava.Game.GameState;
import Gnava.Game.Managers.Listeners.GameDayListener;
import Gnava.Game.Settlements.Settlement;
import Gnava.Interface.Menu.GameFrameMenuBar;
import Gnava.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Interface.Renderers.EventListRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.net.URL;
import java.util.function.Consumer;

// TODO: Put all components into private fields
public class GameFrame extends JFrame {
    private final GameState gameState;

    private static final Dimension PREFERRED_SIZE = new Dimension(400, 600);
    private final GameFrameMenuBar menu;

    private final DefaultListModel<Settlement> settlementListModel = new DefaultListModel<>();
    private final DefaultListModel<GameEvent> eventListModel = new DefaultListModel<>();

    private final Consumer<Settlement> settlementListener = this::onSettlementsChanged;
    private final GameDayListener timeListener = this::onTimeAdvanced;

    private final JList<Settlement> settlementList = new JList<>(settlementListModel);
    private final JList<GameEvent> eventList = new JList<>(eventListModel);
    private final JLabel currentDayValueLabel = new JLabel("0");

    public GameFrame(GameState gameState, String title) {
        super(title);
        this.gameState = gameState;
        menu = new GameFrameMenuBar(gameState, this);
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
        JButton advanceTimeButton = new JButton("Pass time");

        advanceTimeButton.addActionListener(e -> gameState.getTimeManager().advanceTime());

        topPanel.add(advanceTimeButton);
        topPanel.add(currentTimeLabel);
        topPanel.add(currentDayValueLabel);

        return topPanel;
    }

    private JPanel buildBottomPanel() {
        eventList.setCellRenderer(new EventListRenderer());
        JScrollPane eventScrollPane = new JScrollPane(eventList);
        eventScrollPane.setBorder(BorderFactory.createTitledBorder("Events"));

        JScrollPane settlementScrollPane = new JScrollPane(settlementList);
        settlementScrollPane.setBorder(BorderFactory.createTitledBorder("Settlements"));

        JPanel bottom = new JPanel(new GridLayout(2, 1, 5, 5));
        bottom.add(eventScrollPane);
        bottom.add(settlementScrollPane);
        return bottom;
    }

    private void registerListeners() {
        gameState.getSettlementManager().addSettlementCreatedListener(settlementListener);
        gameState.getTimeManager().addTimeAdvancedListener(timeListener);
        gameState.getGameEventsManager().addEventGeneratedListener(this::onReceivedGameEvent);
        gameState.getVictoryConditionsManager().addGameOutcomeListener(this::onGameOutcomeReceived);

        settlementList.addListSelectionListener(onSettlementSelected());
        eventList.addListSelectionListener(onEventSelected());
    }

    private void onSettlementsChanged(Settlement newSettlement) {
        SwingUtilities.invokeLater(() -> {
            settlementListModel.clear();
            for (Settlement s : gameState.getSettlementManager().getSettlements()) {
                settlementListModel.addElement(s);
            }
        });
    }

    private void insertEvent(GameEvent e) {
        eventListModel.add(0, e);
    }

    private void onTimeAdvanced(int currentDay) {
        SwingUtilities.invokeLater(() -> currentDayValueLabel.setText(String.valueOf(currentDay)));
    }

    private void onReceivedGameEvent(GameEvent gameEvent) {
        insertEvent(gameEvent);
    }

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
                    sb.append("Population: ").append(selected.getTotalPopulation()).append("/").append(selected.getTotalPopulation()).append("<br>");

                    new PlaintextPopup(this, sb.toString()).show();
                    settlementList.setSelectedValue(null, false);
                }
            }
        };
    }

    private ListSelectionListener onEventSelected() {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                GameEvent selected = eventList.getSelectedValue();
                if (selected != null) {
                    new PlaintextPopup(this, selected.getDescription()).show();
                    eventList.setSelectedValue(null, false);
                }
            }
        };
    }
}

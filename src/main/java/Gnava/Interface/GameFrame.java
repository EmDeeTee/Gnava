package Gnava.Interface;

import Gnava.Game.Events.GameEvent;
import Gnava.Game.GameState;
import Gnava.Game.Settlements.Settlement;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

// TODO: Put all components into private fields
public class GameFrame extends JFrame {
    private static final Dimension PREFERRED_SIZE = new Dimension(400, 600);
    private static GameFrame instance = null;

    private final DefaultListModel<Settlement> settlementListModel = new DefaultListModel<>();
    private final DefaultListModel<GameEvent> eventListModel = new DefaultListModel<>();

    private final Consumer<List<Settlement>> settlementListener = this::onSettlementsChanged;
    private final Consumer<Integer> timeListener = this::onTimeAdvanced;

    private final JList<Settlement> settlementList = new JList<>(settlementListModel);
    private final JLabel currentDayValueLabel = new JLabel("0");

    private GameFrame() {
        super("Gnava");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(PREFERRED_SIZE);
        setResizable(false);

        setupGui();
        registerListeners();

        pack();
        setLocationRelativeTo(null);
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
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
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        topPanel.setBackground(Color.LIGHT_GRAY);

        JLabel currentTimeLabel = new JLabel("Current day:");
        JButton advanceTimeButton = new JButton("Pass time");

        advanceTimeButton.addActionListener(e -> GameState.getInstance().advanceTime());

        topPanel.add(advanceTimeButton);
        topPanel.add(currentTimeLabel);
        topPanel.add(currentDayValueLabel);

        return topPanel;
    }

    private JPanel buildBottomPanel() {
        JList<GameEvent> eventList = new JList<>(eventListModel);
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
        GameState.getInstance().addSettlementListener(settlementListener);
        GameState.getInstance().addTimeListener(timeListener);

        settlementList.addListSelectionListener(onSettlementSelected());
    }

    private void onSettlementsChanged(List<Settlement> settlements) {
        SwingUtilities.invokeLater(() -> {
            settlementListModel.clear();
            for (Settlement s : settlements) {
                settlementListModel.addElement(s);
            }
        });
    }

    private void onTimeAdvanced(Integer day) {
        SwingUtilities.invokeLater(() -> currentDayValueLabel.setText(String.valueOf(day)));
    }

    private ListSelectionListener onSettlementSelected() {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                Settlement selected = settlementList.getSelectedValue();
                if (selected != null) {
                    messagePlayer("Selected: " + selected.name() + " with pop type of " + selected.populationType());
                }
            }
        };
    }
}

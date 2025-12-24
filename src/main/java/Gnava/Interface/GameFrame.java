package Gnava.Interface;

import Gnava.Game.Events.GameEvent;
import Gnava.Game.GameState;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static GameFrame instance = null;

    private GameFrame() {
        super("Gnava");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setResizable(false);

        setupGui();
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

    public void messagePlayer(String msg) {

    }

    private void setupGui() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);

        JList<GameEvent> eventList = new JList<>();
        JScrollPane eventScrollPane = new JScrollPane(eventList);
        eventScrollPane.setBorder(BorderFactory.createTitledBorder("Events"));

        DefaultListModel<String> settlementModel = new DefaultListModel<>();
        JList<String> settlementList = new JList<>(settlementModel);
        JScrollPane settlementScrollPane = new JScrollPane(settlementList);
        settlementScrollPane.setBorder(BorderFactory.createTitledBorder("Settlements"));

        GameState.getInstance().addSettlementListener(list -> {
            SwingUtilities.invokeLater(() -> {
                System.out.println("Hello");
                settlementModel.clear();
                for (var s : list) {
                    settlementModel.addElement(s.getName());
                }
            });
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1, 5, 5));
        bottomPanel.add(eventScrollPane);
        bottomPanel.add(settlementScrollPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        add(splitPane);
    }
}

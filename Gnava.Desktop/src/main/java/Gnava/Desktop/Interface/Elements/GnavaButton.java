package Gnava.Desktop.Interface.Elements;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GnavaButton extends JButton {
    private static final Color DEFAULT_BG = SystemColor.control;
    private static final Color HOVER_BG = SystemColor.controlHighlight;
    private static final Color PRESSED_BG = SystemColor.controlShadow;
    private static final Color BORDER_COLOR = Color.GRAY;
    private static final Color TEXT_COLOR = Color.BLACK;

    public GnavaButton(String text) {
        super(text);
        applyStyle();
    }

    private void applyStyle() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(DEFAULT_BG);
        setForeground(TEXT_COLOR);

        Border lineBorder = BorderFactory.createLineBorder(BORDER_COLOR, 1);
        Border padding = BorderFactory.createEmptyBorder(5, 6, 5, 6);

        setBorder(BorderFactory.createCompoundBorder(lineBorder, padding));

        setFocusPainted(false);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_BG);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_BG);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(PRESSED_BG);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (contains(e.getPoint())) {
                    setBackground(HOVER_BG);
                } else {
                    setBackground(DEFAULT_BG);
                }
            }
        });
    }
}

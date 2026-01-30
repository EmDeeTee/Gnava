package Gnava.Interface.Renderers;

import javax.swing.*;
import java.awt.*;

public class EventListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
        JList<?> list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus
    ) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (index == 0) {
            setFont(getFont().deriveFont(Font.BOLD, 14f));
        } else {
            setFont(getFont().deriveFont(Font.PLAIN, 14f));
        }

        return this;
    }
}

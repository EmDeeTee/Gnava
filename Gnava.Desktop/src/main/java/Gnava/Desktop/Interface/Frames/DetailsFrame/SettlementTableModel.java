package Gnava.Desktop.Interface.Frames.DetailsFrame;

import Gnava.Core.Settlements.Settlement;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public final class SettlementTableModel extends AbstractTableModel {
    private final List<Settlement> settlements;

    private static final String[] COLUMNS = {
        "Name",
        "Population",
        "Capacity",
        "Type",
        "Wealth",
        "Player"
    };

    public SettlementTableModel(List<Settlement> settlements) {
        this.settlements = settlements;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public int getRowCount() {
        return settlements.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Settlement s = settlements.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> s.getName();
            case 1 -> s.getTotalPopulation();
            case 2 -> s.getMaxPopulation();
            case 3 -> s.getPopulationType();
            case 4 -> s.getWealthLevel();
            case 5 -> s.isPlayer();
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return switch (column) {
            case 1, 2 -> Integer.class;
            case 5 -> Boolean.class;
            default -> String.class;
        };
    }

    public Settlement getSettlement(int row) {
        return settlements.get(row);
    }
}

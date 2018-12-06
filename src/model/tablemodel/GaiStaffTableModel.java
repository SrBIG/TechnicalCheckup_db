package model.tablemodel;

import model.entity.Officer;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GaiStaffTableModel extends AbstractTableModel {
    private List<Officer> officers;

    public GaiStaffTableModel(List<Officer> officers) {
        this.officers = officers;
    }

    @Override
    public int getRowCount() {
        return officers.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Surname";
            case 2:
                return "Rank";
            case 3:
                return "Position";
            default:
                return "Error";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return officers.get(rowIndex).getName();
            case 1:
                return officers.get(rowIndex).getSurname();
            case 2:
                return officers.get(rowIndex).getRank();
            case 3:
                return officers.get(rowIndex).getPosition();
            default:
                return "Error";
        }
    }
}

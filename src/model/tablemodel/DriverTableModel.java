package model.tablemodel;

import model.entity.Driver;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DriverTableModel extends AbstractTableModel {
    List<Driver> drivers;

    public DriverTableModel(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public int getRowCount() {
        return drivers.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Name";
            case 1:
                return "Surname";
            case 2:
                return "Driver license";
            case 3:
                return "Address";
            case 4:
                return "Sex";
            case 5:
                return "Year of birth";
            default:
                return "Error";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return drivers.get(rowIndex).getName();
            case 1:
                return drivers.get(rowIndex).getSurname();
            case 2:
                return drivers.get(rowIndex).getDriverLicense();
            case 3:
                return drivers.get(rowIndex).getAddress();
            case 4:
                return drivers.get(rowIndex).getSex();
            case 5:
                return drivers.get(rowIndex).getYearBirth();
            default:
                return "Error";
        }
    }
}

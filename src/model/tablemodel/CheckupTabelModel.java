package model.tablemodel;

import model.entity.TechnicalCheckup;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CheckupTabelModel extends AbstractTableModel {
    List<TechnicalCheckup> technicalCheckupList;

    public CheckupTabelModel(List<TechnicalCheckup> technicalCheckupList) {
        this.technicalCheckupList = technicalCheckupList;
    }

    @Override
    public int getRowCount() {
        return technicalCheckupList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Date";
            case 1:
                return "Driver's name";
            case 2:
                return "Car's number";
            case 3:
                return "Officer name";
            case 4:
                return "Result";
            default:
                return "Error";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return technicalCheckupList.get(rowIndex).getDate();
            case 1:
                return technicalCheckupList.get(rowIndex).getDriverName();
            case 2:
                return technicalCheckupList.get(rowIndex).getCarNumber();
            case 3:
                return technicalCheckupList.get(rowIndex).getGaiEmployeeName();
            case 4:
                if(technicalCheckupList.get(rowIndex).getResult() == 1){
                    return "success";
                } else if (technicalCheckupList.get(rowIndex).getResult() == 0){
                    return "fail";
                } else {
                    return "no data";
                }
            default:
                return "Error";
        }
    }
}

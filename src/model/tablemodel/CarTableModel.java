package model.tablemodel;

import model.entity.Car;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CarTableModel extends AbstractTableModel {
    List<Car> cars;

    public CarTableModel(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Autos number";
            case 1:
                return "Registrations number";
            case 2:
                return "Engines number";
            case 3:
                return "Color";
            case 4:
                return "Model";
            default:
                return "Error";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return cars.get(rowIndex).getNumber();
            case 1:
                return cars.get(rowIndex).getRegistrationCertificate();
            case 2:
                return cars.get(rowIndex).getEngineNumber();
            case 3:
                return cars.get(rowIndex).getColor();
            case 4:
                return cars.get(rowIndex).getModel();
            default:
                return "Error";
        }
    }
}

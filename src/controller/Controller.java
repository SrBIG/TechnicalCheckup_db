package controller;

import model.entity.Car;
import model.entity.Driver;
import model.entity.Officer;
import model.entity.TechnicalCheckup;
import model.enums.EntityName;
import model.enums.EntityPar;
import model.tablemodel.CarTableModel;
import model.tablemodel.CheckupTabelModel;
import model.tablemodel.DriverTableModel;
import model.tablemodel.GaiStaffTableModel;
import view.MainFrame;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    ControllerEntity controllerEntity;
    MainFrame frame;

    DriverTableModel driverTableModel;
    CarTableModel carTableModel;
    GaiStaffTableModel gaiStaffTableModel;
    CheckupTabelModel checkupTabelModel;

    public Controller() {
        controllerEntity = new ControllerEntity();
        driverTableModel = new DriverTableModel(controllerEntity.getDrivers());
        carTableModel = new CarTableModel(controllerEntity.getCars());
        gaiStaffTableModel = new GaiStaffTableModel(controllerEntity.getOfficers());
        checkupTabelModel = new CheckupTabelModel(controllerEntity.getTechnicalCheckupList());
        frame = MainFrame.of(driverTableModel, carTableModel, gaiStaffTableModel, checkupTabelModel, this);
    }

    public void changeValue(TableModel currentTableModel, int row, int column, String value) {
        EntityName entity = null;
        EntityPar par = null;
        if (currentTableModel.equals(driverTableModel)) {
            entity = EntityName.DRIVER;
            switch (column) {
                case 0:
                    par = EntityPar.NAME;
                    break;
                case 1:
                    par = EntityPar.SURNAME;
                    break;
                case 2:
                    par = EntityPar.DRIVER_LICENSE;
                    break;
                case 3:
                    par = null;
                    break;
                case 4:
                    par = EntityPar.SEX;
                    break;
                case 5:
                    par = EntityPar.YEAR_BIRTH;
                    break;

            }
        } else if (currentTableModel.equals(carTableModel)) {
            entity = EntityName.CAR;
            switch (column) {
                case 0:
                    par = EntityPar.NUMBER;
                    break;
                case 1:
                    par = EntityPar.REG_CERTIFICATE;
                    break;
                case 2:
                    par = EntityPar.ENGINE_NUMBER;
                    break;
                case 3:
                    par = EntityPar.COLOR;
                    break;
                case 4:
                    par = EntityPar.MODEL;
                    break;
            }
        } else if (currentTableModel.equals(gaiStaffTableModel)) {
            entity = EntityName.GAIEMPLOYEE;
            switch (column) {
                case 0:
                    par = EntityPar.NAME;
                    break;
                case 1:
                    par = EntityPar.SURNAME;
                    break;
                case 2:
                    par = EntityPar.RANK;
                    break;
                case 3:
                    par = EntityPar.POSITION;
                    break;
            }
        }

        if (par == null || entity == null) {
            return;
        }
        controllerEntity.changeValue(entity, par, row, value);
        ((AbstractTableModel) currentTableModel).fireTableDataChanged();
    }

    public void deleteRecord(TableModel currentTableModel, int row) {
        EntityName entity = null;
        if (currentTableModel.equals(driverTableModel)) {
            entity = EntityName.DRIVER;
        } else if (currentTableModel.equals(carTableModel)) {
            entity = EntityName.CAR;
        } else if (currentTableModel.equals(gaiStaffTableModel)) {
            entity = EntityName.GAIEMPLOYEE;
        } else if (currentTableModel.equals(checkupTabelModel)) {
            entity = EntityName.TECHNICALCHECKUP;
        }
        controllerEntity.deleteRecord(entity, row);
        ((AbstractTableModel) currentTableModel).fireTableDataChanged();
    }

    public List<Car> getCarInTime(String fisrtDate, String lastDate) {
        List<Car> result = new ArrayList<>();
        try {
            Date begin = new SimpleDateFormat("yyyy-mm-dd").parse(fisrtDate);
            Date end = new SimpleDateFormat("yyyy-mm-dd").parse(lastDate);
            for (TechnicalCheckup checkup : controllerEntity.getTechnicalCheckupList()) {
                Date checkupDate = new Date(checkup.getDate().getTime());
                if(checkupDate.after(begin) && checkupDate.before(end)){
                    int idCar = checkup.getIdCar();
                    result.add(controllerEntity.getCars().get(idCar-1));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TechnicalCheckup> getCheckupInTime(String curDate){
        List<TechnicalCheckup> result = new ArrayList<>();
        try {
            Date currentDate = new SimpleDateFormat("yyyy-mm-dd").parse(curDate);
            for (TechnicalCheckup checkup : controllerEntity.getTechnicalCheckupList()) {
                Date checkupDate = new Date(checkup.getDate().getTime());
                if(checkupDate.getDate() == currentDate.getDate()){
                    result.add(checkup);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TechnicalCheckup> getCarCheckupsHistory(String number){
        List<TechnicalCheckup> result = new ArrayList<>();
        for (TechnicalCheckup checkup : controllerEntity.getTechnicalCheckupList()) {
            if(checkup.getCarNumber().equals(number)){
                result.add(checkup);
            }
        }
        return result;
    }

    public void addDriver(String name,
                          String surname,
                          String driverLicense,
                          String sex,
                          String yearBirth,
                          String country,
                          String city,
                          String street,
                          String house,
                          String flat) {
        controllerEntity.addDriver(
                name,
                surname,
                driverLicense,
                sex,
                yearBirth,
                country,
                city,
                street,
                house,
                flat);
        driverTableModel.fireTableDataChanged();
    }

    public void addCar(String number, String reg, String engine, String color, String model) {
        controllerEntity.addCar(number, reg, engine, color, model);
        carTableModel.fireTableDataChanged();
    }

    public void addOfficer(String name, String surname, String rank, String position) {
        controllerEntity.addOfficer(name, surname, rank, position);
        gaiStaffTableModel.fireTableDataChanged();
    }

    public void addCheckup(Driver driver, Car car, Officer officer, Integer result) {
        controllerEntity.addCheckup(driver, car, officer, result);
        checkupTabelModel.fireTableDataChanged();
    }

    public void saveToDb() {
        controllerEntity.saveToDatabase();
    }

    public List<Driver> getDrivers() {
        return controllerEntity.getDrivers();
    }

    public List<Officer> getOfficers() {
        return controllerEntity.getOfficers();
    }

    public List<Car> getCars() {
        return controllerEntity.getCars();
    }

    public List<TechnicalCheckup> getTechnicalCheckups() {
        return controllerEntity.getTechnicalCheckupList();
    }
}

package model.entity;

import java.sql.Timestamp;

public class TechnicalCheckup {
    private Timestamp date;
    private Integer idDriver;
    private String driverName;
    private Integer idCar;
    private String carNumber;
    private Integer idGaiEmployee;
    private String gaiEmployeeName;
    private Integer result;

    public TechnicalCheckup(Timestamp date,
                            Integer idDriver, String driverName,
                            Integer idCar, String carNumber,
                            Integer idGaiEmployee, String gaiEmployeeName,
                            Integer result) {
        this.date = date;
        this.idDriver = idDriver;
        this.driverName = driverName;
        this.idCar = idCar;
        this.carNumber = carNumber;
        this.idGaiEmployee = idGaiEmployee;
        this.gaiEmployeeName = gaiEmployeeName;
        this.result = result;
    }

    public TechnicalCheckup(Timestamp date,
                            String driverName,
                            String carNumber,
                            String gaiEmployeeName,
                            Integer result) {
        this.date = date;
        this.driverName = driverName;
        this.carNumber = carNumber;
        this.gaiEmployeeName = gaiEmployeeName;
        this.result = result;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getGaiEmployeeName() {
        return gaiEmployeeName;
    }

    public void setGaiEmployeeName(String gaiEmployeeName) {
        this.gaiEmployeeName = gaiEmployeeName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Integer idDriver) {
        this.idDriver = idDriver;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public Integer getIdGaiEmployee() {
        return idGaiEmployee;
    }

    public void setIdGaiEmployee(Integer idGaiEmployee) {
        this.idGaiEmployee = idGaiEmployee;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}

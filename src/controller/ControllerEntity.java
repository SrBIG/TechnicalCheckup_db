package controller;

import model.enums.EntityPar;
import model.enums.EntityName;
import model.dao.*;
import model.entity.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ControllerEntity {
    private DriverDAO driverDAO = new DriverDAO();
    private CarDAO carDAO = new CarDAO();
    private AddressDAO addressDAO = new AddressDAO();
    private OfficerDAO officerDAO = new OfficerDAO();
    private TechnicalCheckupDAO technicalCheckupDAO = new TechnicalCheckupDAO();

    private List<Driver> drivers;
    private List<Car> cars;
    private List<Address> addresses;
    private List<Officer> officers;
    private List<TechnicalCheckup> technicalCheckupList;

    public ControllerEntity(List<Driver> drivers,
                            List<Car> cars,
                            List<Address> addresses,
                            List<Officer> officers,
                            List<TechnicalCheckup> technicalCheckupsList) {
        this.drivers = drivers;
        this.cars = cars;
        this.addresses = addresses;
        this.officers = officers;
        this.technicalCheckupList = technicalCheckupsList;
    }

    public ControllerEntity() {
        getAllFromDb();
    }

    public void changeValue(EntityName entity, EntityPar par, int numEntity, String value) {
        switch (entity) {
            case DRIVER:
                switch (par) {
                    case NAME:
                        drivers.get(numEntity).setName(value);
                        break;
                    case SURNAME:
                        drivers.get(numEntity).setSurname(value);
                        break;
                    case YEAR_BIRTH:
                        drivers.get(numEntity).setYearBirth(Integer.valueOf(value));
                        break;
                    case DRIVER_LICENSE:
                        drivers.get(numEntity).setDriverLicense(value);
                        break;
                    case SEX:
                        drivers.get(numEntity).setSex(value);
                        break;
                }
                break;
            case CAR:
                switch (par) {
                    case NUMBER:
                        cars.get(numEntity).setNumber(value);
                        break;
                    case REG_CERTIFICATE:
                        cars.get(numEntity).setRegistrationCertificate(value);
                        break;
                    case ENGINE_NUMBER:
                        cars.get(numEntity).setEngineNumber(value);
                        break;
                    case COLOR:
                        cars.get(numEntity).setColor(value);
                        break;
                    case MODEL:
                        cars.get(numEntity).setModel(value);
                        break;
                }
                break;
            case GAIEMPLOYEE:
                switch (par) {
                    case NAME:
                        officers.get(numEntity).setName(value);
                        break;
                    case SURNAME:
                        officers.get(numEntity).setSurname(value);
                        break;
                    case RANK:
                        officers.get(numEntity).setRank(value);
                        break;
                    case POSITION:
                        officers.get(numEntity).setPosition(value);
                        break;
                }
        }
    }

    public void deleteRecord(EntityName entity, int num) {
        switch (entity) {
            case DRIVER:
                drivers.remove(num);
                break;
            case CAR:
                cars.remove(num);
                break;
            case ADDRESS:
                addresses.remove(num);
                break;
            case GAIEMPLOYEE:
                officers.remove(num);
                break;
            case TECHNICALCHECKUP:
                technicalCheckupList.remove(num);
                break;
        }
    }

    public void getAllFromDb() {
        drivers = getDriversFromDB();
        cars = getCarsFromDB();
        addresses = getAddressesFromDB();
        officers = getGaiEmployeesFromDB();
        technicalCheckupList = getTechnicalCheckupListFromDB();
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
        Address address = addAddress(country,
                city,
                street,
                house,
                flat);
        drivers.add(new Driver(
                drivers.size() + 1,
                name,
                surname,
                driverLicense,
                address,
                sex,
                Integer.valueOf(yearBirth)));
    }

    private Address addAddress(String country, String city, String street, String house, String flat) {

        Address address = new Address(
                addresses.size() + 1,
                country,
                city,
                street,
                house,
                flat);
        addresses.add(address);
        return address;
    }

    public void addCar(String number,
                       String reg,
                       String engine,
                       String color,
                       String model) {
        cars.add(new Car(
                addresses.size() + 1,
                number,
                reg,
                engine,
                color,
                model));
    }

    public void addOfficer(String name,
                           String surname,
                           String rank,
                           String position) {
        officers.add(new Officer(
                officers.size() + 1,
                name,
                surname,
                rank,
                position));
    }

    public void addCheckup(Driver driver, Car car, Officer officer, Integer result) {
        Date date = new Date();
        technicalCheckupList.add(
                new TechnicalCheckup(
                        new Timestamp(date.getTime()),
                        driver.getId(), driver.getName() + " " + driver.getSurname(),
                        car.getId(), car.getNumber(),
                        officer.getId(), officer.getName() + " " + officer.getSurname(),
                        result
                )
        );
    }

    public void saveToDatabase(){
        addressDAO.saveToDB(addresses);
        driverDAO.saveToDB(drivers);
        carDAO.saveToDB(cars);
        officerDAO.saveToDB(officers);
        technicalCheckupDAO.saveToDB(technicalCheckupList);
    }

    private List<Driver> getDriversFromDB() {
        return driverDAO.getAll();
    }

    private List<Car> getCarsFromDB() {
        return carDAO.getAll();
    }

    private List<Address> getAddressesFromDB() {
        return addressDAO.getAll();
    }

    private List<Officer> getGaiEmployeesFromDB() {
        return officerDAO.getAll();
    }

    private List<TechnicalCheckup> getTechnicalCheckupListFromDB() {
        return technicalCheckupDAO.getAll();
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Officer> getOfficers() {
        return officers;
    }

    public List<TechnicalCheckup> getTechnicalCheckupList() {
        return technicalCheckupList;
    }
}

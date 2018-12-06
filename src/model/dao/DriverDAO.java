package model.dao;

import model.entity.Address;
import model.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO extends AbstractDAO<Driver, Integer> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;

    private Integer id;
    private String name;
    private String surname;
    private String driverLicense;
    private Address address;
    private Integer idAddress;
    private String sex;
    private Integer yearBirth;
    private AddressDAO addressDAO;

    public DriverDAO() {
        addressDAO = new AddressDAO();
    }

    @Override
    public List<Driver> getAll() {
        query = "select * from driver";
        try {
            connection = createConnection(connection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                getRowValue();
                drivers.add(new Driver(id, name, surname, driverLicense, address, sex, yearBirth));
            }
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return drivers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Driver getEntityById(Integer id) {
        query = "select * from driver where id = ?";
        try {
            connection = createConnection(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            getRowValue();
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return new Driver(id, name, surname, driverLicense, address, sex, yearBirth);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveToDB(List<Driver> drivers) {
        deleteAllFromDB("driver", connection);
        for (Driver driver : drivers) {
            query = "insert into driver (id, name, surname, number_driver_license, id_address, sex, year_birth)" +
                    "values(?, ?, ?, ?, ?, ?, ?)";
            try {
                connection = createConnection(connection);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, driver.getId());
                preparedStatement.setString(2, driver.getName());
                preparedStatement.setString(3, driver.getSurname());
                preparedStatement.setString(4, driver.getDriverLicense());
                preparedStatement.setInt(5, driver.getAddress().getId());
                preparedStatement.setString(6, driver.getSex());
                preparedStatement.setInt(7, driver.getYearBirth());
                preparedStatement.executeUpdate();
                preparedStatement.cancel();
                closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public void getRowValue() throws SQLException {
        id = resultSet.getInt(1);
        name = resultSet.getString(2);
        surname = resultSet.getString(3);
        driverLicense = resultSet.getString(4);
        idAddress = resultSet.getInt(5);
        address = addressDAO.getEntityById(idAddress);
        sex = resultSet.getString(6);
        yearBirth = resultSet.getInt(7);
    }
}

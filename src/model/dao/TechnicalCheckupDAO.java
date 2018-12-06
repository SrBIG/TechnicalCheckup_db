package model.dao;

import model.entity.TechnicalCheckup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalCheckupDAO extends AbstractDAO<TechnicalCheckup, Integer> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;

    private Timestamp date;
    private Integer idDriver;
    private String driverName;
    private Integer idCar;
    private String carNumber;
    private Integer idGaiEmployee;
    private String gaiEmployeeName;
    private Integer result;

    @Override
    public List<TechnicalCheckup> getAll() {
        query = "select technical_checkup.date, technical_checkup.result,\n" +
                "       driver.id, driver.name, driver.surname,\n" +
                "       car.id, car.government_number,\n" +
                "       staff_gai.id, staff_gai.name, staff_gai.surname\n" +
                "FROM technical_checkup\n" +
                "JOIN driver on technical_checkup.id_driver = driver.id\n" +
                "JOIN car on technical_checkup.id_car = car.id\n" +
                "JOIN staff_gai on technical_checkup.id_gai_officer = staff_gai.id";
        try {
            connection = createConnection(connection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<TechnicalCheckup> technicalCheckupList = new ArrayList<>();
            while (resultSet.next()) {
                getRowValue();
                technicalCheckupList.add(
                        new TechnicalCheckup(date,
                                idDriver, driverName,
                                idCar, carNumber,
                                idGaiEmployee, gaiEmployeeName,
                                result));
            }
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return technicalCheckupList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TechnicalCheckup getEntityById(Integer id) {
        query = "select technical_checkup.date, technical_checkup.result,\n" +
                "       driver.id, driver.name, driver.surname,\n" +
                "       car.id, car.government_number,\n" +
                "       staff_gai.id, staff_gai.name, staff_gai.surname\n" +
                "FROM technical_checkup\n" +
                "JOIN driver on technical_checkup.id_driver = driver.id\n" +
                "JOIN car on technical_checkup.id_car = car.id\n" +
                "JOIN staff_gai on technical_checkup.id_gai_officer = staff_gai.id\n" +
                "WHERE id = ?";
        try {
            connection = createConnection(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            getRowValue();
            resultSet.close();
            preparedStatement.cancel();
            closeConnection(connection);
            return new TechnicalCheckup(date,
                    idDriver, driverName,
                    idCar, carNumber,
                    idGaiEmployee, gaiEmployeeName,
                    result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveToDB(List<TechnicalCheckup> checkups) {
        deleteAllFromDB("technical_checkup", connection);
        for (TechnicalCheckup checkup : checkups) {
            query = "insert into technical_checkup (date, id_driver, id_car, id_gai_officer, result) " +
                    "values(?, ?, ?, ?, ?)";
            try {
                connection = createConnection(connection);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setTimestamp(1, checkup.getDate());
                preparedStatement.setInt(2, checkup.getIdDriver());
                preparedStatement.setInt(3, checkup.getIdCar());
                preparedStatement.setInt(4, checkup.getIdGaiEmployee());
                preparedStatement.setInt(5, checkup.getResult());
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
        date = resultSet.getTimestamp(1);
        result = resultSet.getInt(2);
        idDriver = resultSet.getInt(3);
        driverName = resultSet.getString(4) + " " + resultSet.getString(5);
        idCar = resultSet.getInt(6);
        carNumber = resultSet.getString(7);
        idGaiEmployee = resultSet.getInt(8);
        gaiEmployeeName = resultSet.getString(9) + " " + resultSet.getString(10);
    }
}

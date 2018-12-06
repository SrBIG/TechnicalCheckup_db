package model.dao;

import model.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends AbstractDAO<Car, Integer> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;


    private Integer id;
    private String number;
    private String registrationCertificate;
    private String engineNumber;
    private String color;
    private String model;

    @Override
    public List<Car> getAll() {
        query = "select * from car";
        try {
            connection = createConnection(connection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                getRowValue();
                cars.add(new Car(id, number, registrationCertificate, engineNumber, color, model));
            }
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Car getEntityById(Integer id) {
        query = "select * from car where id = ?";
        try {
            connection = createConnection(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            getRowValue();
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return new Car(id, number, registrationCertificate, engineNumber, color, model);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveToDB(List<Car> cars) {
        deleteAllFromDB("car", connection);
        for (Car car : cars) {
            query = "insert into car (id, government_number, num_reg_certificate, engine_number, color, model)" +
                    "values(?, ?, ?, ?, ?, ?)";
            try {
                connection = createConnection(connection);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, car.getId());
                preparedStatement.setString(2, car.getNumber());
                preparedStatement.setString(3, car.getRegistrationCertificate());
                preparedStatement.setString(4, car.getEngineNumber());
                preparedStatement.setString(5, car.getColor());
                preparedStatement.setString(6, car.getModel());
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
        number = resultSet.getString(2);
        registrationCertificate = resultSet.getString(3);
        engineNumber = resultSet.getString(4);
        color = resultSet.getString(5);
        model = resultSet.getString(6);
    }
}

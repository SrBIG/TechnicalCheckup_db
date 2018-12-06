package model.dao;

import model.entity.Officer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfficerDAO extends AbstractDAO<Officer, Integer> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;


    private Integer id;
    private String name;
    private String surname;
    private String rank;
    private String position;

    @Override
    public List<Officer> getAll() {
        query = "select * from staff_gai";
        try {
            connection = createConnection(connection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Officer> officers = new ArrayList<>();
            while (resultSet.next()) {
                getRowValue();
                officers.add(new Officer(id, name, surname, rank, position));
            }
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return officers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Officer getEntityById(Integer id) {
        query = "select * from staff_gai where id = ?";
        try {
            connection = createConnection(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            getRowValue();
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return new Officer(id, name, surname, rank, position);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveToDB(List<Officer> officers) {
        deleteAllFromDB("staff_gai", connection);
        for (Officer officer : officers) {
            query = "insert into staff_gai (id, name, surname, rank, position)" +
                    "values(?, ?, ?, ?, ?)";
            try {
                connection = createConnection(connection);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, officer.getId());
                preparedStatement.setString(2, officer.getName());
                preparedStatement.setString(3, officer.getSurname());
                preparedStatement.setString(4, officer.getRank());
                preparedStatement.setString(5, officer.getPosition());
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
        rank = resultSet.getString(4);
        position = resultSet.getString(5);
    }
}

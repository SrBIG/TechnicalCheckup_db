package model.dao;

import model.entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

                    public class AddressDAO extends AbstractDAO<Address, Integer> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;

    private Integer id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;

    @Override
    public List<Address> getAll() {
        query = "select * from address";
        try {
            connection = createConnection(connection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Address> addresses = new ArrayList<>();
            while (resultSet.next()) {
                getRowValue();
                addresses.add(new Address(id, country, city, street, house, flat));
            }
            resultSet.close();
            statement.cancel();
            closeConnection(connection);
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Address getEntityById(Integer id) {
        query = "select * from address where id = ?";
        try {
            connection = createConnection(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                getRowValue();
            }
            resultSet.close();
            preparedStatement.cancel();
            closeConnection(connection);
            return new Address(id, country, city, street, house, flat);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveToDB(List<Address> addresses) {
        deleteAllFromDB("address", connection);
        for (Address address : addresses) {
            query = "insert into address (id, country, city, street, house, flat)" +
                    "values(?, ?, ?, ?, ?, ?)";
            try {
                connection = createConnection(connection);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, address.getId());
                preparedStatement.setString(2, address.getCountry());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getStreet());
                preparedStatement.setString(5, address.getHouse());
                preparedStatement.setString(6, address.getFlat());
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
        country = resultSet.getString(2);
        city = resultSet.getString(3);
        street = resultSet.getString(4);
        house = resultSet.getString(5);
        flat = resultSet.getString(6);
    }
}

package model.dao;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<E, K> {

    public AbstractDAO() {

    }

    public abstract List<E> getAll();

    public abstract E getEntityById(K id);

    public abstract boolean saveToDB(List<E> entity);

    protected void deleteAllFromDB(String dbName, Connection connection){
        try {
            String query = "DELETE FROM " + dbName;
            connection = createConnection(connection);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.cancel();
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void getRowValue() throws SQLException;

    public Connection createConnection(Connection connection) {
        String url = "jdbc:mysql://localhost:3306/GAI?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Connection failed...");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

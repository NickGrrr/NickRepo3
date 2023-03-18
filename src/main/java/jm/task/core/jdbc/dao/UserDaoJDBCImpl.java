package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String tableCreate = "CREATE TABLE IF NOT EXISTS user ("
                + "id BIGINT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "age TINYINT, "
                + "PRIMARY KEY(id))";
        try {
            preparedStatement = connection.prepareStatement(tableCreate);

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String tableDrop = "DROP TABLE IF EXISTS user";

        try {
            preparedStatement = connection.prepareStatement(tableDrop);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String addUser = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(addUser);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String removeUserByID = "DELETE FROM user WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(removeUserByID);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = getConnection();
        List<User> usersList = new ArrayList<>();
        Statement statement = null;

        String getAllUsers = "SELECT id, name, lastName, age FROM user";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsers);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                usersList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String cleanTable = "TRUNCATE TABLE user";

        try {
            preparedStatement = connection.prepareStatement(cleanTable);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}

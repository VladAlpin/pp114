package jm.task.core.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = getConnection();
    public UserDaoJDBCImpl() {}

    @Override
    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "last_name VARCHAR(255) NOT NULL, " +
                    "age TINYINT NOT NULL)");

        } catch (SQLException e) {
            System.err.println("no create table" + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            System.err.println("no drop table" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, last_name, age) VALUES (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();

        } catch (SQLException e) {
            System.err.println("no save user" + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("no remove user" + e);
        }
    }

    @Override
    public List<jm.task.core.jdbc.model.User> getAllUsers() {
        List<jm.task.core.jdbc.model.User> users = new ArrayList<>();

        try(ResultSet result = connection.createStatement().executeQuery("SELECT * FROM user")) {
            while (result.next()) {
                jm.task.core.jdbc.model.User user = new jm.task.core.jdbc.model.User(
                        result.getString("name"),
                        result.getString("last_name"),
                        result.getByte("age"));
                user.setId(result.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("no get all user" + e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE user");
        } catch (SQLException e) {
            System.err.println("no clean user" + e);
        }
    }
}

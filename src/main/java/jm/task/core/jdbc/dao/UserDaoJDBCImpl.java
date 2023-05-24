package jm.task.core.jdbc.dao;

import com.mysql.cj.Session;
import com.mysql.cj.util.Util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = getConnection();
    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)");
        } catch (SQLException e) {
            System.err.println("no create table" + e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            System.err.println("no drop table" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
        } catch (SQLException e) {
            System.err.println("no save user" + e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("no remove user" + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(ResultSet result = connection.createStatement().executeQuery("SELECT * FROM user")) {
            while (result.next()) {
                User user = new User(result.getString("name"), result.getString("lastName"), result.getByte("age"));
                user.setId(result.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("no get all user" + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE user");
        } catch (SQLException e) {
            System.err.println("no clean user" + e);
        }
    }
}

package sk.fifodemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.fifodemo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DataAccess} class provides methods to interact with a database.
 * It allows adding users, retrieving all users, and deleting all users from the database.
 * The database is initialized in-memory with H2 during the creation of a {@code DataAccess} instance.
 */
public class DataAccess {
    private static final Logger logger = LoggerFactory.getLogger(DataAccess.class);
    private static final String JDBC_URL = "jdbc:h2:mem:fifo;DB_CLOSE_DELAY=-1";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public DataAccess() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS SUSERS (USER_ID INT PRIMARY KEY, USER_GUID VARCHAR(64), USER_NAME VARCHAR(64))";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Database initialization failed", e);
        }
    }

    public void add(User user) {
        String sql = "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserGuid());
            statement.setString(3, user.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to add user", e);
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT USER_ID, USER_GUID, USER_NAME FROM SUSERS";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("USER_ID");
                String guid = rs.getString("USER_GUID");
                String name = rs.getString("USER_NAME");
                users.add(new User(id, guid, name));
            }
        } catch (SQLException e) {
            logger.error("Failed to get users", e);
        }
        return users;
    }

    public void deleteAll() {
        String sql = "DELETE FROM SUSERS";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Failed to delete all users", e);
        }
    }
}
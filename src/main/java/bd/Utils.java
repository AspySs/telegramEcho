package bd;

import com.example.testtask.entity.User;

import java.sql.*;


public class Utils {
    private static final String URL = "jdbc:postgresql://localhost:5432/users";
    private static final String USER = "postgres";
    private static final String PASSWORD = "02tidivu";

    private static final String UPDATE_COUNT = "update user_counter set counter = ? where username = ?";
    private static final String GT_USR = "select * from user_counter u where u.username = ?";


    public void updateCounterByUsername(String username, Long counter) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT)) {
            preparedStatement.setLong(1, counter);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public User getUser(String username) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GT_USR)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new User(rs.getString("username"), rs.getLong("counter"), rs.getString("lastmsg"));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

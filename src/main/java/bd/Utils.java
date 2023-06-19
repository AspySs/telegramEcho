package bd;

import com.example.testtask.entity.User;

import java.sql.*;

public class Utils {
    private final static String url = "jdbc:postgresql://localhost:5432/users";
    private final static String user = "postgres";
    private final static String password = "02tidivu";

    private static final String updateCount = "update user_counter set counter = ? where username = ?";
    private static final String gtUsr = "select * from user_counter u where u.username = ?";




    public void updateCounterByUsername(String username, Long counter){
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateCount);) {
            preparedStatement.setLong(1, counter);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public User getUser(String username){
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(gtUsr);) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new User(rs.getString("username"), rs.getLong("counter"), rs.getString("lastmsg"));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

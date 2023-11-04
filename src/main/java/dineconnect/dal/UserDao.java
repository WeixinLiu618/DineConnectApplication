package dineconnect.dal;

import dineconnect.model.User;

import java.sql.*;
import java.util.Date;

/**
 * @author Weixin Liu
 */
public class UserDao {
    protected ConnectionManager connectionManager;
    private static UserDao userDao = null;

    protected UserDao() {
        connectionManager = new ConnectionManager();
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public User create(User user) throws SQLException {
        String insertUserSQL = "INSERT INTO Users(UserId,UserName,YelpingSince) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUserSQL);
            insertStmt.setString(1, user.getUserId());
            insertStmt.setString(2, user.getUserName());
            insertStmt.setTimestamp(3, new Timestamp(user.getYelpingSince().getTime()));
            insertStmt.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public User getUserByUserId(String userId) throws SQLException {
        String selectUserByUserIdSQL = "SELECT * FROM Users WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUserByUserIdSQL);
            selectStmt.setString(1, userId);
            result = selectStmt.executeQuery();
            if(result.next()){
                String userName = result.getString("UserName");
                Date yelpingSince = new Date(result.getTimestamp("YelpingSince").getTime());
                User user = new User(userId, userName, yelpingSince);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }


}

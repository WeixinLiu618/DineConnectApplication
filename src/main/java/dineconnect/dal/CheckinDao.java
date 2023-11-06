package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Checkin;
import dineconnect.model.User;

import java.sql.*;

public class CheckinDao {
    protected ConnectionManager connectionManager;
    private static CheckinDao checkinDao = null;

    protected CheckinDao() {
        connectionManager = new ConnectionManager();
    }

    public static CheckinDao getInstance() {
        if (checkinDao == null) {
            checkinDao = new CheckinDao();
        }
        return checkinDao;
    }

    public Checkin create(String businessId, String userId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        Business newBusiness;
        if (businessDao.getBusinessByBusinessId(businessId) == null) {
            newBusiness = new Business(businessId);
            businessDao.create(newBusiness);
        } else {
            newBusiness = businessDao.getBusinessByBusinessId(businessId);
        }

        UserDao userDao = UserDao.getInstance();
        User newUser;
        if (userDao.getUserByUserId(userId) == null) {
            newUser = new User(userId);
            userDao.create(newUser);
        } else {
            newUser =  userDao.getUserByUserId(userId);
        }

        String insertCheckinSQL = "INSERT INTO CheckIns(CheckInTime, UserId, BusinessId) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCheckinSQL);
            Date currentTime = Date.valueOf(String.valueOf(System.currentTimeMillis()));
            insertStmt.setDate(1, currentTime);
            insertStmt.setString(2, businessId);
            insertStmt.setString(3, userId);
            int affectedRows = insertStmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Add record into CheckIn ERROR.");
            } else {
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedCheckInId = generatedKeys.getInt(1);
                    return new Checkin(generatedCheckInId, currentTime, newUser, newBusiness);
                }
            }
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
        return null;
    }
}

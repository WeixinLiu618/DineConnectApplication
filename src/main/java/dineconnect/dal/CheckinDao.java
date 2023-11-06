package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Checkin;
import dineconnect.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Checkin create(Checkin checkin) throws SQLException {
        String insertCheckinSQL = "INSERT INTO CheckIns(CheckInTime, UserId, BusinessId) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCheckinSQL,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(checkin.getCheckInTime().getTime()));
            insertStmt.setString(2, checkin.getUser().getUserId());
            insertStmt.setString(3, checkin.getBusiness().getBusinessId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int checkinId = -1;
            if (resultKey.next()) {
                checkinId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            checkin.setCheckInId(checkinId);
            return checkin;
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
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }


    public Checkin getCheckinByCheckinId(int checkinId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectCheckinByCheckinIdSQL = "SELECT * FROM CheckIns WHERE CheckIns.CheckInId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCheckinByCheckinIdSQL);
            selectStmt.setInt(1, checkinId);
            result = selectStmt.executeQuery();
            if (result.next()) {
                int resultCheckinId = result.getInt("CheckInId");
                Date createdTime = new Date(result.getTimestamp("CheckInTime").getTime());
                String userId = result.getString("UserId");
                String businessId = result.getString("BusinessId");

                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Checkin checkin = new Checkin(resultCheckinId, createdTime, user, business);
                return checkin;
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

    public List<Checkin> getCheckinsByBusinessId(String businessId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectCheckinByBusinessIdSQL = "SELECT * FROM CheckIns WHERE CheckIns.BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Checkin> checkinsList = new ArrayList<Checkin>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCheckinByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                int checkinId = result.getInt("CheckInId");
                Date createdTime = new Date(result.getTimestamp("CheckInTime").getTime());
                String userId = result.getString("UserId");

                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Checkin checkin = new Checkin(checkinId, createdTime, user, business);
                checkinsList.add(checkin);
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

        return checkinsList;
    }

    public List<Checkin> getCheckinsByUserId(String userId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectCheckinByUserIdSQL = "SELECT * FROM Checkins WHERE Checkins.UserId=?;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Checkin> checkinsList = new ArrayList<Checkin>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCheckinByUserIdSQL);
            selectStmt.setString(1, userId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                int checkinId = result.getInt("CheckInId");
                Date createdTime = new Date(result.getTimestamp("CheckInTime").getTime());
                String businessId = result.getString("BusinessId");

                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Checkin checkin = new Checkin(checkinId, createdTime, user, business);
                checkinsList.add(checkin);
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

        return checkinsList;
    }
}

package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Tip;
import dineconnect.model.User;

import java.sql.*;

public class TipDao {
    protected ConnectionManager connectionManager;
    private static TipDao tipDao = null;

    protected TipDao() {
        connectionManager = new ConnectionManager();
    }

    public static TipDao getInstance() {
        if (tipDao == null) {
            tipDao = new TipDao();
        }
        return tipDao;
    }

    public Tip create(String businessId, String userId, String tips) throws SQLException {
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

        String insertTipSQL = "INSERT INTO Tips(Text, CreatedTime, UserId, BusinessId) VALUES (?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTipSQL);
            Date currentTime = Date.valueOf(String.valueOf(System.currentTimeMillis()));
            insertStmt.setString(1, tips);
            insertStmt.setDate(2, currentTime);
            insertStmt.setString(3, businessId);
            insertStmt.setString(4, userId);
            int affectedRows = insertStmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Add record into Tips ERROR.");
            } else {
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedTipsId = generatedKeys.getInt(1);
                    return new Tip(generatedTipsId, tips, currentTime, newUser, newBusiness);
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

    public Tip getTipByBusinessId(String businessId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectTipByBusinessIdSQL = "SELECT Businesses.BusinessName, Tips.Text, Tips.CreatedTime FROM Tips " +
                "JOIN Businesses ON Tips.BusinessId = Businesses.BusinessId WHERE Reviews.BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTipByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            if (result.next()) {
                int tipId = result.getInt("TipId");
                String text = result.getString("Text");
                Date createdTime = Date.valueOf(result.getString("CreatedTime"));
                String userID = result.getString("UserId");
                String resultBusinessId = result.getString("BusinessId");
                Tip tip = new Tip(tipId, text, createdTime, userDao.getUserByUserId(userID), businessDao.getBusinessByBusinessId(businessId));
                return tip;
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

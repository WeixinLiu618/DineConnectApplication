package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Tip;
import dineconnect.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Tip create(Tip tip) throws SQLException {
        String insertTipSQL = "INSERT INTO Tips(Text, CreatedTime, UserId, BusinessId) VALUES (?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTipSQL,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, tip.getText());
            insertStmt.setTimestamp(2, new Timestamp(tip.getCreatedTime().getTime()));
            insertStmt.setString(3, tip.getUser().getUserId());
            insertStmt.setString(4, tip.getBusiness().getBusinessId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int tipId = -1;
            if (resultKey.next()) {
                tipId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            tip.setTipId(tipId);
            return tip;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultKey != null) {
                resultKey.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Tip getTipByTipId(int tipId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectTipByTipIdSQL = "SELECT * FROM Tips WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTipByTipIdSQL);
            selectStmt.setInt(1, tipId);
            result = selectStmt.executeQuery();
            if (result.next()) {
                int resultTipId = result.getInt("TipId");
                String text = result.getString("Text");
                Date createdTime = new Date(result.getTimestamp("CreatedTime").getTime());
                String userId = result.getString("UserId");
                String businessId = result.getString("BusinessId");

                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Tip tip = new Tip(resultTipId, text, createdTime, user, business);
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

    public List<Tip> getTipByBusinessId(String businessId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectTipByBusinessIdSQL = "SELECT * FROM Tips WHERE BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Tip> tipsList = new ArrayList<Tip>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTipByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                int tipId = result.getInt("TipId");
                String text = result.getString("Text");
                Date createdTime = new Date(result.getTimestamp("CreatedTime").getTime());
                String userID = result.getString("UserId");
                Tip tip = new Tip(tipId, text, createdTime, userDao.getUserByUserId(userID), businessDao.getBusinessByBusinessId(businessId));
                tipsList.add(tip);
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

        return tipsList;
    }

    public List<Tip> getTipsByUserId(String userId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectTipByUserIdSQL = "SELECT * FROM Tips WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Tip> tipsList = new ArrayList<Tip>();
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTipByUserIdSQL);
            selectStmt.setString(1, userId);
            result = selectStmt.executeQuery();
            while(result.next()) {
                int tipId = result.getInt("TipId");
                String text = result.getString("Text");
                Date createdTime = new Date(result.getTimestamp("CreatedTime").getTime());
                String businessId = result.getString("BusinessId");
                Tip tip = new Tip(tipId, text, createdTime, userDao.getUserByUserId(userId), businessDao.getBusinessByBusinessId(businessId));
                tipsList.add(tip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(result != null) {
                result.close();
            }
        }

        return tipsList;
    }

    public Tip delete(Tip tip) throws SQLException {
        String deleteTipSQL = "DELETE FROM Tips WHERE TipId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteTipSQL);
            deleteStmt.setInt(1, tip.getTipId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}

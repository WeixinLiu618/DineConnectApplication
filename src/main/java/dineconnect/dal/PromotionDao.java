package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Promotion;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionDao {
    protected ConnectionManager connectionManager;
    private static PromotionDao promotionDao = null;

    protected PromotionDao() {
        connectionManager = new ConnectionManager();
    }

    public static PromotionDao getInstance() {
        if (promotionDao == null) {
            promotionDao = new PromotionDao();
        }
        return promotionDao;
    }

    public Promotion create(Promotion promotion) throws SQLException {

        String insertPromotionSQL = "INSERT into Promotions(BusinessId, StartTime, EndTime, Event) VALUES (?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPromotionSQL, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, promotion.getBusiness().getBusinessId());
            insertStmt.setTimestamp(2, new Timestamp(promotion.getStartTime().getTime()));
            insertStmt.setTimestamp(3, new Timestamp(promotion.getEndTime().getTime()));
            insertStmt.setString(4, promotion.getEvent());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int promotionId = -1;
            if (resultKey.next()) {
                promotionId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            promotion.setPromotionId(promotionId);
            return promotion;

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

    public Promotion getPromotionByPromotionId(int promotionId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        String selectPromotionByPromotionIdSQL = "SELECT * FROM Promotions WHERE PromotionId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotionByPromotionIdSQL);
            selectStmt.setInt(1, promotionId);
            result = selectStmt.executeQuery();
            if (result.next()) {
                Date startTime = new Date(result.getTimestamp("StartTime").getTime());
                Date endTime = new Date(result.getTimestamp("EndTime").getTime());
                String event = result.getString("Event");
                String businessId = result.getString("BusinessId");
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Promotion promotion = new Promotion(promotionId, business, startTime, endTime, event);
                return promotion;
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

    public List<Promotion> getPromotionsByBusinessId(String businessId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        String selectPromotionByBusinessIdSQL = "SELECT * FROM Promotions WHERE BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Promotion> promotionList = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotionByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                int promotionId = result.getInt("PromotionId");
                Date startTime = new Date(result.getTimestamp("StartTime").getTime());
                Date endTime = new Date(result.getTimestamp("EndTime").getTime());
                String event = result.getString("Event");
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Promotion promotion = new Promotion(promotionId, business, startTime, endTime, event);
                promotionList.add(promotion);
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
        return promotionList;
    }

    public Promotion delete(Promotion promotion) throws SQLException {
        String deletePromotionSQL = "DELETE FROM Promotions WHERE PromotionId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePromotionSQL);
            deleteStmt.setInt(1, promotion.getPromotionId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}

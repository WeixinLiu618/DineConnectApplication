package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Promotion;

import java.sql.*;
import java.util.ArrayList;
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

    public Promotion create(String businessId, String startTime, String endTime, String event) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        Business newBusiness;
        if (businessDao.getBusinessByBusinessId(businessId) == null) {
            newBusiness = new Business(businessId);
            businessDao.create(newBusiness);
        } else {
            newBusiness = businessDao.getBusinessByBusinessId(businessId);
        }

        String insertPromotionSQL = "INSERT into Promotions(BusinessId, StartTime, EndTime, Event) VALUES (?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPromotionSQL);
            insertStmt.setString(1, businessId);
            insertStmt.setDate(2, Date.valueOf(startTime));
            insertStmt.setDate(3, Date.valueOf(endTime));
            insertStmt.setString(4, event);
            int affectedRows = insertStmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Add record into Promotions ERROR.");
            } else {
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedTipsId = generatedKeys.getInt(1);
                    return new Promotion(generatedTipsId, newBusiness, Date.valueOf(startTime), Date.valueOf(endTime), event);
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

    public List<PromotionInfoDemo> getPromotionByBusinessId(String businessId) throws SQLException {
        String selectPromotionByBusinessIdSQL = "SELECT Businesses.BusinessName, Promotions.StartTime, Promotions.EndTime, " +
                "Promotions.Event FROM Promotions JOIN Businesses ON Promotions.BusinessId = Businesses.BusinessId WHERE BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<PromotionInfoDemo> promotionList = new ArrayList<PromotionInfoDemo>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotionByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                String businessName = result.getString("BusinessName");
                Date startTime = Date.valueOf(result.getString("StartTime"));
                Date endTime = Date.valueOf(result.getString("EndTime"));
                String event = result.getString("Event");
                PromotionInfoDemo promotionInfoDemo = new PromotionInfoDemo(businessName, startTime, endTime, event);
                promotionList.add(promotionInfoDemo);
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

    private class PromotionInfoDemo {
        String businessName;
        Date startTime;
        Date endTime;
        String event;

        public PromotionInfoDemo(String businessName, Date startTime, Date endTime, String event) {
            this.businessName = businessName;
            this.startTime = startTime;
            this.endTime = endTime;
            this.event = event;
        }

        @Override
        public String toString() {
            return "Promotion{" +
                    ", businessName='" + businessName + '\'' +
                    ", StartTime='" + startTime + '\'' +
                    ", EndTime='" + endTime + '\'' +
                    ", Event='" + event + '\'' +
                    '}';
        }
    }
}

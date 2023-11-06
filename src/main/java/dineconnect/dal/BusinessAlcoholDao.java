package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.BusinessAlcohol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessAlcoholDao {
    protected ConnectionManager connectionManager;
    private static BusinessAlcoholDao businessAlcoholDao = null;

    protected BusinessAlcoholDao() {
        connectionManager = new ConnectionManager();
    }

    public static BusinessAlcoholDao getInstance() {
        if (businessAlcoholDao == null) {
            businessAlcoholDao = new BusinessAlcoholDao();
        }
        return businessAlcoholDao;
    }

    public BusinessAlcohol create(BusinessAlcohol businessAlcohol) throws SQLException {
        CityDao cityDao = CityDao.getInstance();
        if (cityDao.getCityByPostalCode(businessAlcohol.getCity().getPostalCode()) == null) {
            cityDao.create(businessAlcohol.getCity());
        }
        BusinessDao businessDao = BusinessDao.getInstance();
        if (businessDao.getBusinessByBusinessId(businessAlcohol.getBusinessId()) == null) {
            businessDao.create(new Business(businessAlcohol.getBusinessId()));
        }

        String insertBusinessSQL = "INSERT INTO BusinessAlcohol (BusinessId, AlcoholType) VALUES (?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBusinessSQL);
            insertStmt.setString(1, businessAlcohol.getBusinessId());
            insertStmt.setString(2, businessAlcohol.getAlcoholType().name());
            insertStmt.executeUpdate();
            return businessAlcohol;
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

    public BusinessAlcohol getBusinessAlcoholByBusinessId(String businessId) throws SQLException {
        String selectBusinessByBusinessIdSQL = "SELECT * FROM BusinessAlcohol WHERE BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBusinessByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            selectStmt.executeQuery();
            result = selectStmt.executeQuery();
            if (result.next()) {
                String resultBusinessId = result.getString("BusinessId");
                BusinessAlcohol.AlcoholType alcoholType = BusinessAlcohol.AlcoholType.valueOf(result.getString("AlcoholType"));
                return new BusinessAlcohol(resultBusinessId, alcoholType);
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

    public BusinessAlcohol updateBusinessAlcohol(BusinessAlcohol businessAlcohol) throws SQLException {
        String updateBusinessByBusinessIdSQL = "UPDATE BusinessAlcohol SET AlcoholType=? where BusinessId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateBusinessByBusinessIdSQL);
            updateStmt.setString(1, businessAlcohol.getAlcoholType().name());
            updateStmt.setString(2, businessAlcohol.getBusinessId());
            updateStmt.executeUpdate();
            return businessAlcohol;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (updateStmt != null) {
                updateStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

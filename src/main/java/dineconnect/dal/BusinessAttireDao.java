package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.BusinessAttire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessAttireDao {
    protected ConnectionManager connectionManager;
    private static BusinessAttireDao businessAttireDao = null;

    protected BusinessAttireDao() {
        connectionManager = new ConnectionManager();
    }

    public static BusinessAttireDao getInstance() {
        if (businessAttireDao == null) {
            businessAttireDao = new BusinessAttireDao();
        }
        return businessAttireDao;
    }

    public BusinessAttire create(BusinessAttire businessAttire) throws SQLException {
        CityDao cityDao = CityDao.getInstance();
        if (cityDao.getCityByPostalCode(businessAttire.getCity().getPostalCode()) == null) {
            cityDao.create(businessAttire.getCity());
        }
        BusinessDao businessDao = BusinessDao.getInstance();
        if (businessDao.getBusinessByBusinessId(businessAttire.getBusinessId()) == null) {
            businessDao.create(new Business(businessAttire.getBusinessId()));
        }

        String insertBusinessSQL = "INSERT INTO BusinessAttire (BusinessId, AttireType) VALUES (?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBusinessSQL);
            insertStmt.setString(1, businessAttire.getBusinessId());
            insertStmt.setString(2, businessAttire.getAttireType().name());
            insertStmt.executeUpdate();
            return businessAttire;
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

    public BusinessAttire getBusinessAttireByBusinessId(String businessId) throws SQLException {
        String selectBusinessByBusinessIdSQL = "SELECT * FROM BusinessAttire WHERE BusinessId=?;";
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
                BusinessAttire.AttireType businessAttire = BusinessAttire.AttireType.valueOf(result.getString("AttireType"));
                return new BusinessAttire(resultBusinessId, businessAttire);
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

    public BusinessAttire updateBusinessAttire(BusinessAttire businessAttire) throws SQLException {
        String updateBusinessByBusinessIdSQL = "UPDATE BusinessAttire SET AttireType=? where BusinessId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateBusinessByBusinessIdSQL);
            updateStmt.setString(1, businessAttire.getAttireType().name());
            updateStmt.setString(2, businessAttire.getBusinessId());
            updateStmt.executeUpdate();
            return businessAttire;
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

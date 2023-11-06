package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.BusinessByAppointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessByAppointmentDao {
    protected ConnectionManager connectionManager;
    private static BusinessByAppointmentDao businessByAppointmentDao = null;

    protected BusinessByAppointmentDao() {
        connectionManager = new ConnectionManager();
    }

    public static BusinessByAppointmentDao getInstance() {
        if (businessByAppointmentDao == null) {
            businessByAppointmentDao = new BusinessByAppointmentDao();
        }
        return businessByAppointmentDao;
    }

    public BusinessByAppointment create(BusinessByAppointment businessByAppointment) throws SQLException {
        CityDao cityDao = CityDao.getInstance();
        if (cityDao.getCityByPostalCode(businessByAppointment.getCity().getPostalCode()) == null) {
            cityDao.create(businessByAppointment.getCity());
        }
        BusinessDao businessDao = BusinessDao.getInstance();
        if (businessDao.getBusinessByBusinessId(businessByAppointment.getBusinessId()) == null) {
            businessDao.create(new Business(businessByAppointment.getBusinessId()));
        }

        String insertBusinessSQL = "INSERT INTO BusinessByAppointment (BusinessId, ByAppointmentOnly) VALUES (?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBusinessSQL);
            insertStmt.setString(1, businessByAppointment.getBusinessId());
            insertStmt.setBoolean(2, businessByAppointment.isByAppointmentOnly());
            insertStmt.executeUpdate();
            return businessByAppointment;
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

    public BusinessByAppointment getBusinessByAppointmentByBusinessId(String businessId) throws SQLException {
        String selectBusinessByBusinessIdSQL = "SELECT * FROM BusinessByAppointment WHERE BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBusinessByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            selectStmt.executeUpdate();
            result = selectStmt.executeQuery();
            if (result.next()) {
                String resultBusinessId = result.getString("BusinessId");
                boolean byAppointmentOnly = result.getBoolean("ByAppointmentOnly");
                return new BusinessByAppointment(resultBusinessId, byAppointmentOnly);
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

    public BusinessByAppointment updateBusinessByAppointment(BusinessByAppointment businessByAppointment) throws SQLException {
        String updateBusinessByBusinessIdSQL = "UPDATE BusinessByAppointment SET ByAppointmentOnly=? where BusinessId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateBusinessByBusinessIdSQL);
            updateStmt.setBoolean(1, businessByAppointment.isByAppointmentOnly());
            updateStmt.setString(2, businessByAppointment.getBusinessId());
            updateStmt.executeUpdate();
            return businessByAppointment;
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

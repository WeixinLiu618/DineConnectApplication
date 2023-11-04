package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.City;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Weixin Liu
 */
public class BusinessDao {
    protected ConnectionManager connectionManager;
    private static BusinessDao businessDao = null;

    protected BusinessDao() {
        connectionManager = new ConnectionManager();
    }

    public static BusinessDao getInstance() {
        if (businessDao == null) {
            businessDao = new BusinessDao();
        }
        return businessDao;
    }

    public Business create(Business business) throws SQLException {
        // to check if the postal code is in the current data
        // if not exists then add it
        CityDao cityDao = CityDao.getInstance();
        if (cityDao.getCityByPostalCode(business.getCity().getPostalCode()) == null) {
            cityDao.create(business.getCity());
        }
        String insertBusinessSQL = "INSERT INTO Businesses (BusinessId, BusinessName, BusinessStars, Longitude, Latitude," +
                "Address, MondayListedHours, TuesdayListedHours, WednesdayListedHours, ThursdayListedHours," +
                "FridayListedHours, SaturdayListedHours, SundayListedHours, PostalCode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBusinessSQL);
            insertStmt.setString(1, business.getBusinessId());
            insertStmt.setString(2, business.getBusinessName());
            insertStmt.setBigDecimal(3, business.getBusinessStars());
            insertStmt.setBigDecimal(4, business.getLongitude());
            insertStmt.setBigDecimal(5, business.getLatitude());
            insertStmt.setString(6, business.getAddress());
            insertStmt.setString(7, business.getMondayListedHours());
            insertStmt.setString(8, business.getTuesdayListedHours());
            insertStmt.setString(9, business.getWednesdayListedHours());
            insertStmt.setString(10, business.getThursdayListedHours());
            insertStmt.setString(11, business.getFridayListedHours());
            insertStmt.setString(12, business.getSaturdayListedHours());
            insertStmt.setString(13, business.getSundayListedHours());
            insertStmt.setString(14, business.getCity().getPostalCode());
            insertStmt.executeUpdate();
            return business;
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

    public Business getBusinessByBusinessId(String businessId) throws SQLException {
        String selectBusinessByBusinessIdSQL = "SELECT * FROM Businesses where BusinessId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBusinessByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            CityDao cityDao = CityDao.getInstance();
            if (result.next()) {
                String businessName = result.getString("BusinessName");
                BigDecimal businessStars = result.getBigDecimal("BusinessStars");
                BigDecimal longitude = result.getBigDecimal("Longitude");
                BigDecimal latitude = result.getBigDecimal("Latitude");
                String address = result.getString("Address");
                String mondayListedHours = result.getString("MondayListedHours");
                String tuesdayListedHours = result.getString("TuesdayListedHours");
                String wednesdayListedHours = result.getString("WednesdayListedHours");
                String thursdayListedHours = result.getString("ThursdayListedHours");
                String fridayListedHours = result.getString("FridayListedHours");
                String saturdayListedHours = result.getString("SaturdayListedHours");
                String sundayListedHours = result.getString("SundayListedHours");
                String postalCode = result.getString("PostalCode");
                City city = cityDao.getCityByPostalCode(postalCode);
                Business business = new Business(businessId, businessName, businessStars, longitude, latitude, address,
                        mondayListedHours, tuesdayListedHours, wednesdayListedHours, thursdayListedHours, fridayListedHours,
                        saturdayListedHours, sundayListedHours, city);
                return business;

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

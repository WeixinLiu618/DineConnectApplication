package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.City;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            insertStmt.setDouble(3, business.getBusinessStars());
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
        String selectBusinessByBusinessIdSQL = "SELECT * FROM Businesses WHERE BusinessId=?;";
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
                Double businessStars = result.getDouble("BusinessStars");
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

    public Business updateBusiness(Business business) throws SQLException {
        CityDao cityDao = CityDao.getInstance();
        if (cityDao.getCityByPostalCode(business.getCity().getPostalCode()) == null) {
            cityDao.create(business.getCity());
        }
        String insertBusinessSQL = "UPDATE Businesses SET BusinessName=?, BusinessStars=?, Longitude=?, Latitude=?, Address=?, " +
                "MondayListedHours=?, TuesdayListedHours=?, WednesdayListedHours=?, ThursdayListedHours=?, " +
                "FridayListedHours=?, SaturdayListedHours=?, SundayListedHours=?, PostalCode=? where BusinessId=?;";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBusinessSQL);
            insertStmt.setString(1, business.getBusinessName());
            insertStmt.setDouble(2, business.getBusinessStars());
            insertStmt.setBigDecimal(3, business.getLongitude());
            insertStmt.setBigDecimal(4, business.getLatitude());
            insertStmt.setString(5, business.getAddress());
            insertStmt.setString(6, business.getMondayListedHours());
            insertStmt.setString(7, business.getTuesdayListedHours());
            insertStmt.setString(8, business.getWednesdayListedHours());
            insertStmt.setString(9, business.getThursdayListedHours());
            insertStmt.setString(10, business.getFridayListedHours());
            insertStmt.setString(11, business.getSaturdayListedHours());
            insertStmt.setString(12, business.getSundayListedHours());
            insertStmt.setString(13, business.getCity().getPostalCode());
            insertStmt.setString(14, business.getBusinessId());
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

    public List<Business> getTopClosestBusiness(BigDecimal inputLongitude, BigDecimal inputLatitude) throws SQLException {
        List<Business> businessList = new ArrayList<>();
        String selectTopClosestBusinessSQL =
                "SELECT b.BusinessId, b.BusinessName, b.BusinessStars, b.Longitude, b.Latitude,b.Address," +
                        "b.MondayListedHours,b.TuesdayListedHours,b.WednesdayListedHours,b.ThursdayListedHours," +
                        "b.FridayListedHours,b.SaturdayListedHours,b.SundayListedHours,b.PostalCode," +
                        "COUNT(r.ReviewId) AS NumberOfReviews, AVG(r.CommentStars) AS AverageRating, " +
                        "6371 * acos(cos(radians(?)) * cos(radians(b.Latitude)) * cos(radians(b.Longitude) - radians(?)) " +
                        "+ sin(radians(?)) * sin(radians(b.Latitude))) AS distance \n" +
                        "FROM businesses b JOIN Reviews r ON b.BusinessId = r.BusinessId\n" +
                        "GROUP BY b.BusinessId, b.BusinessName, b.Latitude, b.Longitude\n" +
                        "ORDER BY distance ASC, NumberOfReviews DESC, AverageRating DESC\n" +
                        "LIMIT 10;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTopClosestBusinessSQL);
            selectStmt.setBigDecimal(1, inputLatitude);
            selectStmt.setBigDecimal(2, inputLongitude);
            selectStmt.setBigDecimal(3, inputLatitude);
            result = selectStmt.executeQuery();
            CityDao cityDao = CityDao.getInstance();
            while (result.next()) {
                String businessId = result.getString("BusinessId");
                String businessName = result.getString("BusinessName");
                // change to rating
                Double businessStars = result.getDouble("AverageRating");
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
                businessList.add(business);
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
        return businessList;
    }

    public double getRatingForBusiness(String businessId) throws SQLException {
        Business business = businessDao.getBusinessByBusinessId(businessId);
        double rating = -1.0; // Default value if the business doesn't exist or there is an error
        String selectAvgRatingSQL = "SELECT AVG(r.CommentStars) AS AverageRating " +
                "FROM businesses b " +
                "JOIN Reviews r ON b.BusinessId = r.BusinessId " +
                "WHERE b.BusinessId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAvgRatingSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();

            if (result.next()) {
                rating = result.getDouble("AverageRating");
                business.setBusinessStars(rating);
                businessDao.updateBusiness(business);
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
        return rating;
    }
}

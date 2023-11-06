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
        String selectBusinessByBusinessIdSQL = "SELECT BusinessName, Address, PostalCode, BusinessAlcohol.AlcoholType, BusinessAttire.AttireType, " +
                "CASE WHEN BusinessByAppointment.ByAppointmentOnly = 1 THEN 'Yes' ELSE 'No' END AS ByAppointmentOnly " +
                "FROM Businesses JOIN BusinessAlcohol ON Businesses.BusinessId = BusinessAlcohol.BusinessId " +
                "JOIN BusinessAttire ON Businesses.BusinessId = BusinessAttire.BusinessId " +
                "JOIN BusinessByAppointment ON Businesses.BusinessId = BusinessByAppointment.BusinessId " +
                "WHERE Reviews.BusinessId=?;";
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

    public List<businessInfoDemo> getTopClosestBusiness(BigDecimal longitude, BigDecimal latitude) throws SQLException {
        String selectBusinessByBusinessIdSQL = "SELECT b.BusinessName, concat('Monday: ', " +
                "CASE WHEN b.MondayListedHours IS NULL or b.MondayListedHours = '' THEN 'Closed' else b.MondayListedHours END, '\n Tuesday: ', " +
                "CASE WHEN b.TuesdayListedHours IS NULL or b.TuesdayListedHours = '' THEN 'Closed' else b.TuesdayListedHours END, '\n Wednesday: ', " +
                "CASE WHEN b.WednesdayListedHours IS NULL or b.WednesdayListedHours = '' THEN 'Closed' else b.WednesdayListedHours END, '\n Thursday: ', " +
                "CASE WHEN b.ThursdayListedHours IS NULL or b.ThursdayListedHours = '' THEN 'Closed' else b.ThursdayListedHours END, '\n Friday: ', " +
                "CASE WHEN b.FridayListedHours IS NULL or b.FridayListedHours = '' THEN 'Closed' else b.FridayListedHours END, '\n Saturday: ', " +
                "CASE WHEN b.SaturdayListedHours IS NUL or b.SaturdayListedHours = '' THEN 'Closed' else b.SaturdayListedHours END,'\n Sunday', " +
                "CASE WHEN b.SundayListedHours IS NULL or b.SundayListedHours = '' THEN 'Closed' else b.SundayListedHours END) as Hours, " +
                "concat(b.address, ', ', c.city) as Address, COUNT(r.ReviewId) AS NumberOfReviews, round(AVG(r.CommentStars), 2) AS AverageRating, " +
                "round(6371 * acos( cos( radians(?) ) * cos( radians( b.Latitude ) ) * " +
                "cos( radians( b.Longitude ) - radians(?) ) + sin( radians(?) ) * sin( radians( b.Latitude ) ) ) ,2) AS DistanceInKM " +
                "FROM Businesses b JOIN Reviews r ON b.BusinessId = r.BusinessId " +
                "JOIN Cities c ON b.PostalCode = c.PostalCode WHERE b.Latitude != ? AND b.Longitude != ? " +
                "GROUP BY b.BusinessId, b.BusinessName, c.PostalCode, b.Latitude, b.Longitude ORDER BY DistanceInKM ASC, NumberOfReviews DESC, AverageRating DESC LIMIT 10;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<businessInfoDemo> resultBusinesses = new ArrayList<businessInfoDemo>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBusinessByBusinessIdSQL);
            selectStmt.setBigDecimal(1, latitude);
            selectStmt.setBigDecimal(2, longitude);
            selectStmt.setBigDecimal(3, latitude);
            selectStmt.setBigDecimal(4, latitude);
            selectStmt.setBigDecimal(5, longitude);
            result = selectStmt.executeQuery();

            while (result.next()) {
                String businessName = result.getString("BusinessName");
                String hours = result.getString("Hours");
                String address = result.getString("Address");
                int NumberOfReviews = result.getInt("NumberOfReviews");
                double AverageRating = result.getDouble("AverageRating");
                double DistanceInKM = result.getDouble("DistanceInKM");
                businessInfoDemo business = new businessInfoDemo(businessName, hours, address, NumberOfReviews, AverageRating, DistanceInKM);
                resultBusinesses.add(business);
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

        return resultBusinesses;
    }

    private class businessInfoDemo {
        private String businessName;
        private String hours;
        private String address;
        private int NumberOfReviews;
        private double AverageRating;
        private double DistanceInKM;
        public businessInfoDemo(String businessName, String hours, String address, int NumberOfReviews, double AverageRating, double DistanceInKM) {
            this.businessName = businessName;
            this.hours = hours;
            this.address = address;
            this.NumberOfReviews = NumberOfReviews;
            this.AverageRating = AverageRating;
            this.DistanceInKM = DistanceInKM;
        }

        @Override
        public String toString() {
            return "Business{" +
                    ", businessName='" + businessName + '\'' +
                    ", Hours='" + hours + '\'' +
                    ", address='" + address + '\'' +
                    ", NumberOfReviews='" + NumberOfReviews + '\'' +
                    ", AverageRating='" + AverageRating + '\'' +
                    ", DistanceInKM='" + DistanceInKM + '\'' +
                    '}';
        }
    }

}

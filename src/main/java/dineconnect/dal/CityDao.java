package dineconnect.dal;

import dineconnect.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao {

    protected ConnectionManager connectionManager;
    private static CityDao cityDao = null;

    protected CityDao() {
        connectionManager = new ConnectionManager();
    }

    public static CityDao getInstance() {
        if (cityDao == null) {
            cityDao = new CityDao();
        }
        return cityDao;
    }

    public City create(City city) throws SQLException {
        String insertCitySQL = "INSERT INTO Cities(PostalCode,City,State) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCitySQL);
            insertStmt.setString(1, city.getPostalCode());
            insertStmt.setString(2, city.getCity());
            insertStmt.setString(3, city.getState());
            insertStmt.executeUpdate();
            return city;

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

    public City getCityByPostalCode(String postalCode) throws SQLException {
        String selectCityByPostalCodeSQL = "SELECT * FROM Cities WHERE PostalCode=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCityByPostalCodeSQL);
            selectStmt.setString(1, postalCode);
            result = selectStmt.executeQuery();
            if (result.next()) {
                String cityName = result.getString("City");
                String state = result.getString("State");
                City city = new City(postalCode, cityName, state);
                return city;
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

package dineconnect.model;

/**
 * @author Weixin Liu
 */
public class City {
    protected String postalCode;
    protected String cityName;
    protected String state;

    public City(String postalCode, String cityName, String state) {
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.state = state;
    }

    public City(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "City{" +
                "postalCode='" + postalCode + '\'' +
                ", city='" + cityName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

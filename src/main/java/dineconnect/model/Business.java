package dineconnect.model;

import java.math.BigDecimal;

public class Business {
    private String businessId;
    private String businessName;
    private double businessStars;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    private String mondayListedHours;
    private String tuesdayListedHours;
    private String wednesdayListedHours;
    private String thursdayListedHours;
    private String fridayListedHours;
    private String saturdayListedHours;
    private String sundayListedHours;
    private City city;

    public Business(
            String businessId, String businessName, double businessStars, BigDecimal longitude, BigDecimal latitude,
            String address, String mondayListedHours, String tuesdayListedHours, String wednesdayListedHours,
            String thursdayListedHours, String fridayListedHours, String saturdayListedHours, String sundayListedHours,
            City city
    ) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessStars = businessStars;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.mondayListedHours = mondayListedHours;
        this.tuesdayListedHours = tuesdayListedHours;
        this.wednesdayListedHours = wednesdayListedHours;
        this.thursdayListedHours = thursdayListedHours;
        this.fridayListedHours = fridayListedHours;
        this.saturdayListedHours = saturdayListedHours;
        this.sundayListedHours = sundayListedHours;
        this.city = city;
    }

    public Business(String businessId) {
        this.businessId = businessId;
        this.businessName = "";
        this.businessStars = 0.0;
        this.longitude = BigDecimal.valueOf(0);
        this.latitude = BigDecimal.valueOf(0);
        this.address = "";
        this.mondayListedHours = "";
        this.tuesdayListedHours = "";
        this.wednesdayListedHours = "";
        this.thursdayListedHours = "";
        this.fridayListedHours = "";
        this.saturdayListedHours = "";
        this.sundayListedHours = "";
        this.city = null;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public double getBusinessStars() {
        return businessStars;
    }

    public void setBusinessStars(double businessStars) {
        this.businessStars = businessStars;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMondayListedHours() {
        return mondayListedHours;
    }

    public void setMondayListedHours(String mondayListedHours) {
        this.mondayListedHours = mondayListedHours;
    }

    public String getTuesdayListedHours() {
        return tuesdayListedHours;
    }

    public void setTuesdayListedHours(String tuesdayListedHours) {
        this.tuesdayListedHours = tuesdayListedHours;
    }

    public String getWednesdayListedHours() {
        return wednesdayListedHours;
    }

    public void setWednesdayListedHours(String wednesdayListedHours) {
        this.wednesdayListedHours = wednesdayListedHours;
    }

    public String getThursdayListedHours() {
        return thursdayListedHours;
    }

    public void setThursdayListedHours(String thursdayListedHours) {
        this.thursdayListedHours = thursdayListedHours;
    }

    public String getFridayListedHours() {
        return fridayListedHours;
    }

    public void setFridayListedHours(String fridayListedHours) {
        this.fridayListedHours = fridayListedHours;
    }

    public String getSaturdayListedHours() {
        return saturdayListedHours;
    }

    public void setSaturdayListedHours(String saturdayListedHours) {
        this.saturdayListedHours = saturdayListedHours;
    }

    public String getSundayListedHours() {
        return sundayListedHours;
    }

    public void setSundayListedHours(String sundayListedHours) {
        this.sundayListedHours = sundayListedHours;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessStars=" + businessStars +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", address='" + address + '\'' +
                ", mondayListedHours='" + mondayListedHours + '\'' +
                ", tuesdayListedHours='" + tuesdayListedHours + '\'' +
                ", wednesdayListedHours='" + wednesdayListedHours + '\'' +
                ", thursdayListedHours='" + thursdayListedHours + '\'' +
                ", fridayListedHours='" + fridayListedHours + '\'' +
                ", saturdayListedHours='" + saturdayListedHours + '\'' +
                ", sundayListedHours='" + sundayListedHours + '\'' +
                ", city=" + city +
                '}';
    }
}

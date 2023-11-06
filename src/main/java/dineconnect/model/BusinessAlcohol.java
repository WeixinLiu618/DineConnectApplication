package dineconnect.model;

import java.math.BigDecimal;

public class BusinessAlcohol extends Business{
    private AlcoholType alcoholType;
    public BusinessAlcohol(String businessId, String businessName, double businessStars, BigDecimal longitude,
                           BigDecimal latitude, String address, String mondayListedHours, String tuesdayListedHours,
                           String wednesdayListedHours, String thursdayListedHours, String fridayListedHours,
                           String saturdayListedHours, String sundayListedHours, City city, AlcoholType alcoholType) {
        super(businessId, businessName, businessStars, longitude, latitude, address, mondayListedHours,
                tuesdayListedHours, wednesdayListedHours, thursdayListedHours, fridayListedHours,
                saturdayListedHours, sundayListedHours, city);
        this.alcoholType = alcoholType;
    }

    public BusinessAlcohol(String businessId, AlcoholType alcoholType) {
        super(businessId);
        this.alcoholType = alcoholType;
    }

    public String getBusinessId() {
        return super.getBusinessId();
    }

    public void setBusinessId(String businessId) {
        super.setBusinessId(businessId);
    }

    public AlcoholType getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(AlcoholType alcoholType) {
        this.alcoholType = alcoholType;
    }

    @Override
    public String toString() {
        return "BusinessAlcohol{" +
                "businessId='" + super.toString() + '\'' +
                ", alcoholType=" + alcoholType +
                '}';
    }

    public enum AlcoholType {
        NONE, BEER_AND_WINE, FULL_BAR
    }
}

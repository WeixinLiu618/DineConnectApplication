package dineconnect.model;

import java.math.BigDecimal;

public class BusinessAttire extends Business{
    private AttireType attireType;

    public BusinessAttire(String businessId, String businessName, double businessStars, BigDecimal longitude,
                          BigDecimal latitude, String address, String mondayListedHours, String tuesdayListedHours,
                          String wednesdayListedHours, String thursdayListedHours, String fridayListedHours,
                          String saturdayListedHours, String sundayListedHours, City city, AttireType attireType) {
        super(businessId, businessName, businessStars, longitude, latitude, address, mondayListedHours,
                tuesdayListedHours, wednesdayListedHours, thursdayListedHours, fridayListedHours,
                saturdayListedHours, sundayListedHours, city);
        this.attireType = attireType;
    }

    public BusinessAttire(String businessId, AttireType attireType) {
        super(businessId);
        this.attireType = attireType;
    }

    public String getBusinessId() {
        return super.getBusinessId();
    }

    public void setBusinessId(String businessId) {
        super.setBusinessId(businessId);
    }

    public AttireType getAttireType() {
        return attireType;
    }

    public void setAttireType(AttireType attireType) {
        this.attireType = attireType;
    }

    @Override
    public String toString() {
        return "BusinessAttire{" +
                "businessId='" + super.toString() + '\'' +
                ", attireType=" + attireType +
                '}';
    }

    public enum AttireType {
        CASUAL, DRESSY, FORMAL
    }
}

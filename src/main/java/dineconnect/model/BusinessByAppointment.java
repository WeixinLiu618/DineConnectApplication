package dineconnect.model;

import java.math.BigDecimal;

public class BusinessByAppointment extends Business{
    private boolean byAppointmentOnly;
    public BusinessByAppointment(String businessId, String businessName, double businessStars, BigDecimal longitude,
                                 BigDecimal latitude, String address, String mondayListedHours, String tuesdayListedHours,
                                 String wednesdayListedHours, String thursdayListedHours, String fridayListedHours,
                                 String saturdayListedHours, String sundayListedHours, City city, boolean byAppointmentOnly) {
        super(businessId, businessName, businessStars, longitude, latitude, address, mondayListedHours,
                tuesdayListedHours, wednesdayListedHours, thursdayListedHours, fridayListedHours,
                saturdayListedHours, sundayListedHours, city);
        this.byAppointmentOnly = byAppointmentOnly;
    }

    public BusinessByAppointment(String businessId, boolean byAppointmentOnly) {
        super(businessId);
        this.byAppointmentOnly = byAppointmentOnly;
    }

    public String getBusinessId() {
        return super.getBusinessId();
    }

    public void setBusinessId(String businessId) {
        super.setBusinessId(businessId);
    }

    public boolean isByAppointmentOnly() {
        return byAppointmentOnly;
    }

    public void setByAppointmentOnly(boolean byAppointmentOnly) {
        this.byAppointmentOnly = byAppointmentOnly;
    }

    @Override
    public String toString() {
        return "BusinessByAppointment{" +
                "businessId='" + super.toString() + '\'' +
                ", byAppointmentOnly=" + byAppointmentOnly +
                '}';
    }
}

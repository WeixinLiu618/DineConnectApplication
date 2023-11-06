package dineconnect.model;

import java.util.Date;

public class Checkin {
    private int checkInId;
    private Date checkInTime;
    private User user;
    private Business business;

    public Checkin(int checkInId, Date checkInTime, User user, Business business) {
        this.checkInId = checkInId;
        this.checkInTime = checkInTime;
        this.business = business;
        this.user = user;
    }

    public Checkin(Date checkInTime, User user, Business business) {
        this.checkInTime = checkInTime;
        this.user = user;
        this.business = business;
    }

    public Checkin(User user, Business business) {
        this.user = user;
        this.business = business;
    }

    public int getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(int checkInId) {
        this.checkInId = checkInId;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "checkInId=" + checkInId +
                ", checkInTime=" + checkInTime +
                ", userId='" + user.toString() + '\'' +
                ", businessId='" + business.toString() + '\'' +
                '}';
    }
}

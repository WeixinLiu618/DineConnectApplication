package dineconnect.model;

import java.util.Date;

public class User {
    private String userId;
    private String userName;
    private Date yelpingSince;

    public User(String userId, String userName, Date yelpingSince) {
        this.userId = userId;
        this.userName = userName;
        this.yelpingSince = yelpingSince;
    }

    public User(String userId) {
        this.userId = userId;
        this.userName = "";
        this.yelpingSince = java.sql.Date.valueOf(String.valueOf(System.currentTimeMillis()));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getYelpingSince() {
        return yelpingSince;
    }

    public void setYelpingSince(Date yelpingSince) {
        this.yelpingSince = yelpingSince;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", yelpingSince=" + yelpingSince +
                '}';
    }
}

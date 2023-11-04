package dineconnect.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Weixin Liu
 */
public class User {
    protected String userId;
    protected String userName;
    protected Date yelpingSince;

    public User(String userId) {
        this.userId = userId;
    }


    public User(String userId, String userName, Date yelpingSince) {
        this.userId = userId;
        this.userName = userName;
        this.yelpingSince = yelpingSince;
    }

    public User(String userName, Date yelpingSince) {
        this.userName = userName;
        this.yelpingSince = yelpingSince;
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
                ", yelpingSince=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(yelpingSince) +
                '}';
    }
}

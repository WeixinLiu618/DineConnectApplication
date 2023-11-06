package dineconnect.model;

import java.util.Date;

public class Tip {
    private int tipId;
    private String text;
    private Date createdTime;
    private User user;
    private Business business;

    public Tip(int tipId, String text, Date createdTime, User user, Business business) {
        this.tipId = tipId;
        this.text = text;
        this.createdTime = createdTime;
        this.business = business;
        this.user = user;
    }

    public Tip(String text, Date createdTime, User user, Business business) {
        this.text = text;
        this.createdTime = createdTime;
        this.user = user;
        this.business = business;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
        return "Tip{" +
                "tipId=" + tipId +
                ", text='" + text + '\'' +
                ", createdTime=" + createdTime +
                ", userId='" + user.toString() + '\'' +
                ", businessId='" + business.toString() + '\'' +
                '}';
    }
}

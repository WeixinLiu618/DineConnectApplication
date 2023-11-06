package dineconnect.model;

import java.util.Date;

public class Promotion {
    private int promotionId;
    private Business business;
    private Date startTime;
    private Date endTime;
    private String event;

    public Promotion(int promotionId, Business business, Date startTime, Date endTime, String event) {
        this.promotionId = promotionId;
        this.business = business;
        this.startTime = startTime;
        this.endTime = endTime;
        this.event = event;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusinessId(Business business) {
        this.business = business;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promotionId=" + promotionId +
                ", businessId='" + business + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", event='" + event + '\'' +
                '}';
    }
}

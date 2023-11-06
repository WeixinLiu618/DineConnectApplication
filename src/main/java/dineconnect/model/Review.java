package dineconnect.model;

import java.math.BigDecimal;
import java.util.Date;

public class Review {
    private String reviewId;
    private String comment;
    private Date createdTime;
    private BigDecimal commentStars;
    private Business business;
    private User user;

    public Review(String reviewId, String comment, Date createdTime, BigDecimal commentStars, Business business, User user) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.createdTime = createdTime;
        this.commentStars = commentStars;
        this.business = business;
        this.user = user;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public BigDecimal getCommentStars() {
        return commentStars;
    }

    public void setCommentStars(BigDecimal commentStars) {
        this.commentStars = commentStars;
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
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", comment='" + comment + '\'' +
                ", createdTime=" + createdTime +
                ", commentStars=" + commentStars +
                ", businessId='" + business.toString() + '\'' +
                ", userId='" + user.toString() + '\'' +
                '}';
    }
}

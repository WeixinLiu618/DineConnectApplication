package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Review;
import dineconnect.model.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewDao {
    protected ConnectionManager connectionManager;
    private static ReviewDao reviewDao = null;

    protected ReviewDao() {
        connectionManager = new ConnectionManager();
    }

    public static ReviewDao getInstance() {
        if (reviewDao == null) {
            reviewDao = new ReviewDao();
        }
        return reviewDao;
    }

    public Review create(Review review) throws SQLException {

        String insertReviewSQL = "INSERT INTO Reviews(ReviewId, COMMENT, CreatedTime, CommentStars, BusinessId, UserId) VALUES (?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReviewSQL);

            insertStmt.setString(1, review.getReviewId());
            insertStmt.setString(2, review.getComment());
            insertStmt.setTimestamp(3, new Timestamp(review.getCreatedTime().getTime()));
            insertStmt.setBigDecimal(4, review.getCommentStars());
            insertStmt.setString(5, review.getBusiness().getBusinessId());
            insertStmt.setString(6, review.getUser().getUserId());
            insertStmt.executeUpdate();
            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Review getReviewByReviewId(String reviewId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectReviewByUserIdSQL = "SELECT * FROM Reviews WHERE Reviews.ReviewId=?;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviewByUserIdSQL);
            selectStmt.setString(1, reviewId);
            result = selectStmt.executeQuery();
            if (result.next()) {
                String comment = result.getString("COMMENT");
                Date createdTime = new Date(result.getTimestamp("CreatedTime").getTime());
                BigDecimal commentStars = result.getBigDecimal("CommentStars");
                String businessId = result.getString("BusinessId");
                String userId = result.getString("UserId");
                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Review review = new Review(reviewId, comment, createdTime, commentStars, business, user);
                return review;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return null;
    }


    public List<Review> getReviewsByUserId(String userId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectReviewByUserIdSQL = "SELECT * FROM Reviews WHERE Reviews.UserId=? ORDER BY CreatedTime;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Review> reviewsList = new ArrayList<Review>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviewByUserIdSQL);
            selectStmt.setString(1, userId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                String reviewId = result.getString("ReviewId");
                String comment = result.getString("COMMENT");
                Date createdTime = new Date(result.getTimestamp("CreatedTime").getTime());
                BigDecimal commentStars = result.getBigDecimal("CommentStars");
                String businessId = result.getString("BusinessId");
                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Review review = new Review(reviewId, comment, createdTime, commentStars, business, user);
                reviewsList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return reviewsList;
    }

    public List<Review> getReviewsByBusinessId(String businessId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectReviewsByBusinessIdSQL = "SELECT * FROM Reviews WHERE Reviews.BusinessId=? ORDER BY CreatedTime;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<Review> reviewsList = new ArrayList<Review>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviewsByBusinessIdSQL);
            selectStmt.setString(1, businessId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                String reviewId = result.getString("ReviewId");
                String comment = result.getString("COMMENT");
                Date createdTime = new Date(result.getTimestamp("CreatedTime").getTime());
                BigDecimal commentStars = result.getBigDecimal("CommentStars");
                String userId = result.getString("UserId");
                User user = userDao.getUserByUserId(userId);
                Business business = businessDao.getBusinessByBusinessId(businessId);
                Review review = new Review(reviewId, comment, createdTime, commentStars, business, user);
                reviewsList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return reviewsList;
    }

    public Review delete(Review review) throws SQLException {
        String deleteReviewSQL = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReviewSQL);
            deleteStmt.setString(1, review.getReviewId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }
}

package dineconnect.dal;

import dineconnect.model.Business;
import dineconnect.model.Review;
import dineconnect.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Review create(String comment, double commentStars, String businessId, String userId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        Business newBusiness;
        if (businessDao.getBusinessByBusinessId(businessId) == null) {
            newBusiness = new Business(businessId);
            businessDao.create(newBusiness);
        } else {
            newBusiness = businessDao.getBusinessByBusinessId(businessId);
        }

        UserDao userDao = UserDao.getInstance();
        User newUser;
        if (userDao.getUserByUserId(userId) == null) {
            newUser = new User(userId);
            userDao.create(newUser);
        } else {
            newUser =  userDao.getUserByUserId(userId);
        }

        String insertReviewSQL = "INSERT INTO Reviews(ReviewId, COMMENT, CreatedTime, CommentStars, BusinessId, UserId) VALUES (?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReviewSQL);
            Date currentTime = Date.valueOf(String.valueOf(System.currentTimeMillis()));
            String commentId = getRandomId();
            insertStmt.setString(1, commentId);
            insertStmt.setString(2, comment);
            insertStmt.setDate(3, currentTime);
            insertStmt.setDouble(4, commentStars);
            insertStmt.setString(5, businessId);
            insertStmt.setString(6, userId);
            return new Review(commentId, comment, currentTime, commentStars, newBusiness, newUser);
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

    public List<ReviewInfoDemo> getReviewByUserId(String userId) throws SQLException {
        BusinessDao businessDao = BusinessDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String selectReviewByUserId = "SELECT Businesses.BusinessName, Reviews.comment, Reviews.commentStars, " +
                "Reviews.createdtime FROM Reviews JOIN Businesses ON Reviews.BusinessId = Businesses.BusinessId WHERE Reviews.UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet result = null;
        List<ReviewInfoDemo> reviewInfoDemoList = new ArrayList<ReviewInfoDemo>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviewByUserId);
            selectStmt.setString(1, userId);
            result = selectStmt.executeQuery();
            while (result.next()) {
                String businessName = result.getString("BusinessName");
                String comment = result.getString("comment");
                Double commentStars = result.getDouble("commentStars");
                Date createdTime = Date.valueOf(result.getString("createdtime"));
                ReviewDao.ReviewInfoDemo reviewInfoDemo = new ReviewDao.ReviewInfoDemo(businessName, comment, commentStars, createdTime);
                reviewInfoDemoList.add(reviewInfoDemo);
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

        return reviewInfoDemoList;
    }

    private class ReviewInfoDemo {
        String businessName;
        String comment;
        Double commentStars;
        Date createdTime;
        public ReviewInfoDemo(String businessName, String comment, Double commentStars, Date createdTime) {
            this.businessName = businessName;
            this.comment = comment;
            this.commentStars = commentStars;
            this.createdTime = createdTime;
        }

        @Override
        public String toString() {
            return "Review{" +
                    ", businessName='" + businessName + '\'' +
                    ", Comment='" + comment + '\'' +
                    ", CommentStars='" + commentStars + '\'' +
                    ", CreatedTime='" + createdTime + '\'' +
                    '}';
        }
    }

    /*
    Randomly generated comment ID
     */
    protected String getRandomId() {
        String LETTERS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < 22) { // length of the random string.
            int index = (int) (rnd.nextFloat() * LETTERS.length());
            result.append(LETTERS.charAt(index));
        }
        return result.toString();
    }
}

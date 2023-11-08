package dineconnect.servlet;

import dineconnect.dal.ReviewDao;
import dineconnect.dal.UserDao;
import dineconnect.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Weixin Liu
 */
@WebServlet("/reviewdelete")
public class DeleteReviewServlet extends HttpServlet {
    protected ReviewDao reviewDao;
    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        reviewDao = ReviewDao.getInstance();
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewId = req.getParameter("reviewId");
        try {
            Review toDeleteReview = reviewDao.getReviewByReviewId(reviewId);
            if (toDeleteReview != null) {
                String userId = toDeleteReview.getUser().getUserId();
                req.setAttribute("userid", userId);
                reviewDao.delete(toDeleteReview);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/userhistory").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

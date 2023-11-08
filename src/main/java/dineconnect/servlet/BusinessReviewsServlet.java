package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.ReviewDao;
import dineconnect.dal.UserDao;
import dineconnect.model.Business;
import dineconnect.model.Review;
import dineconnect.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weixin Liu
 */
@WebServlet("/businessreviews")

public class BusinessReviewsServlet extends HttpServlet {
    protected UserDao userDao;
    protected BusinessDao businessDao;
    protected ReviewDao reviewDao;


    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
        businessDao = BusinessDao.getInstance();
        reviewDao = ReviewDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String businessId = req.getParameter("businessId");
        User user = null;
        Business business = null;
        List<Review> reviewList = new ArrayList<>();

        try {
            user = userDao.getUserByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }

        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            reviewList = reviewDao.getReviewsByBusinessId(businessId);
            req.setAttribute("user", user);
            req.setAttribute("business", business);
            req.setAttribute("reviewList", reviewList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/businessReviews.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * @author Weixin Liu
 */

@WebServlet("/addreview")
public class AddReviewServlet extends HttpServlet {
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
        User user = null;
        Business business = null;

        String businessId = req.getParameter("businessId");
        String userId = req.getParameter("userId");
        if (userId == null) {
            // Handle the case where userId is not provided
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            user = userDao.getUserByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Handle the case where user is null
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            if (business == null) {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
            req.setAttribute("userId", userId);
            req.setAttribute("businessId", businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/businessreviews").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        Business business = null;

        String businessId = req.getParameter("businessId");
        String userId = req.getParameter("userId");
        String comment = req.getParameter("comment");
        String commentStars = req.getParameter("commentStars");

        if (userId == null) {
            // Handle the case where userId is not provided
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            user = userDao.getUserByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Handle the case where user is null
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (comment != null && !comment.trim().isEmpty() && commentStars != null && !commentStars.trim().isEmpty()) {
            try {
                business = businessDao.getBusinessByBusinessId(businessId);
                Review review = new Review(String.valueOf(UUID.randomUUID()), comment, new Date(), new BigDecimal(commentStars), business, user);
                reviewDao.create(review);
                resp.sendRedirect(req.getContextPath() + "/businessreviews?businessId=" + businessId + "&userId=" + user.getUserId());
            } catch (SQLException e) {
                doGet(req, resp);
            }
        } else {
            doGet(req, resp);
        }

    }
}

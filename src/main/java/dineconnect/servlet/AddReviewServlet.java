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
        String comment = req.getParameter("comment");
        String commentStars = req.getParameter("commentStars");
        HttpSession session = req.getSession();
        Object userAttribute = session.getAttribute("user");
        if (userAttribute != null) {
            user = (User) userAttribute;
        }
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        if (comment != null && !comment.trim().isEmpty() && commentStars != null && !commentStars.trim().isEmpty()) {
            try {
                business = businessDao.getBusinessByBusinessId(businessId);
                Review review = new Review(String.valueOf(UUID.randomUUID()), comment, new Date(), new BigDecimal(commentStars), business, user);
                review = reviewDao.create(review);
                req.setAttribute("user", user);
                req.setAttribute("business", business);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        req.getRequestDispatcher("/businessreviews").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

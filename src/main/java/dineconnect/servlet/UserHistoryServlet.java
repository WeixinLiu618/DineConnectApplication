package dineconnect.servlet;

import dineconnect.dal.*;
import dineconnect.model.Checkin;
import dineconnect.model.Review;
import dineconnect.model.Tip;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weixin Liu
 */

@WebServlet("/userhistory")
public class UserHistoryServlet extends HttpServlet {

    protected UserDao userDao;
    protected ReviewDao reviewDao;
    protected TipDao tipDao;
    protected CheckinDao checkinDao;


    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
        reviewDao = ReviewDao.getInstance();
        tipDao = TipDao.getInstance();
        checkinDao = CheckinDao.getInstance();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = null;
        Object userAttribute = session.getAttribute("user");
        if (userAttribute != null) {
            user = (User) userAttribute;
        } else {
            String userId = req.getParameter("userid");
            try {
                user = userDao.getUserByUserId(userId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }


        List<Review> reviewList = new ArrayList<>();
        List<Tip> tipList = new ArrayList<>();
        List<Checkin> checkinList = new ArrayList<>();

        Map<String, String> businessNameMap = new HashMap<>();


        if (user != null) {

            try {
                reviewList = reviewDao.getReviewsByUserId(user.getUserId());
                tipList = tipDao.getTipsByUserId(user.getUserId());
                checkinList = checkinDao.getCheckinsByUserId(user.getUserId());
                for (Review r : reviewList) {
                    businessNameMap.put(r.getBusiness().getBusinessId(), r.getBusiness().getBusinessName());
                }
                for (Tip t : tipList) {
                    businessNameMap.put(t.getBusiness().getBusinessId(), t.getBusiness().getBusinessName());
                }
                for (Checkin c : checkinList) {
                    businessNameMap.put(c.getBusiness().getBusinessId(), c.getBusiness().getBusinessName());
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("user", user);
            req.setAttribute("reviewList", reviewList);
            req.setAttribute("tipList", tipList);
            req.setAttribute("checkinList", checkinList);
            req.setAttribute("businessNameMap", businessNameMap);

            req.getRequestDispatcher("/userHistory.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

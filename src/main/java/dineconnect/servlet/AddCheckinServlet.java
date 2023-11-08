package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.CheckinDao;
import dineconnect.dal.UserDao;
import dineconnect.model.Business;
import dineconnect.model.Checkin;
import dineconnect.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/addcheckin")
public class AddCheckinServlet extends HttpServlet {
    protected UserDao userDao;
    protected BusinessDao businessDao;
    protected CheckinDao checkinDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
        businessDao = BusinessDao.getInstance();
        checkinDao = CheckinDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        Business business = null;

        String businessId = req.getParameter("businessId");
        String userId = req.getParameter("userId");

        if (userId == null) {
            // Handle the case where business ID is not provided
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        try {
            user = userDao.getUserByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        req.getRequestDispatcher("/businesscheckins").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            Checkin checkin = new Checkin(new Date(), user, business);
            checkin = checkinDao.create(checkin);
            resp.sendRedirect(req.getContextPath() + "/businesscheckins?businessId=" + businessId + "&userId=" + user.getUserId());
        } catch (SQLException e) {
            doGet(req, resp);
        }

    }
}

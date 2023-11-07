package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.CheckinDao;
import dineconnect.model.Business;
import dineconnect.model.Checkin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/businesscheckins")
public class BusinessCheckinsServlet extends HttpServlet {
    protected BusinessDao businessDao;
    protected CheckinDao checkinDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
        checkinDao = checkinDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessId = req.getParameter("businessId");
        Business business = null;
        List<Checkin> checkinList = new ArrayList<>();
        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            checkinList = checkinDao.getCheckinsByBusinessId(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (business != null) {
            req.setAttribute("business", business);
            req.setAttribute("checkinList", checkinList);
            req.getRequestDispatcher("/businessCheckins.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

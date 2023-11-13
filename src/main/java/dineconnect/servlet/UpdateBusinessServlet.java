package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.model.Business;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Weixin Liu
 */
@WebServlet("/updatebusiness")
public class UpdateBusinessServlet extends HttpServlet {
    protected BusinessDao businessDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String businessId = req.getParameter("businessId");
        Business business = null;
        Object businessAttribute = session.getAttribute("business");
        if (businessAttribute != null) {
            business = (Business) businessAttribute;
        } else {
            try {
                business = businessDao.getBusinessByBusinessId(businessId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("businessId", businessId);
        session.setAttribute("business", business);
        req.getRequestDispatcher("/businessUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessName = req.getParameter("businessName");
        String address = req.getParameter("address");
        System.out.println(address);
        String mondayListedHours = req.getParameter("mondayListedHours");
        String tuesdayListedHours = req.getParameter("tuesdayListedHours");
        String wednesdayListedHours = req.getParameter("wednesdayListedHours");
        String thursdayListedHours = req.getParameter("thursdayListedHours");
        String fridayListedHours = req.getParameter("fridayListedHours");
        String saturdayListedHour = req.getParameter("saturdayListedHour");
        String sundayListedHours = req.getParameter("sundayListedHours");
        String businessId = req.getParameter("businessId");

        HttpSession session = req.getSession();
        Business business = null;
        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            System.out.println("here：" +business);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (business == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        business.setBusinessName(businessName);
        business.setAddress(address);
        business.setMondayListedHours(mondayListedHours);
        business.setTuesdayListedHours(tuesdayListedHours);
        business.setWednesdayListedHours(wednesdayListedHours);
        business.setThursdayListedHours(thursdayListedHours);
        business.setFridayListedHours(fridayListedHours);
        business.setSaturdayListedHours(saturdayListedHour);
        business.setSundayListedHours(sundayListedHours);

        System.out.println(business);
        try {
            businessDao.updateBusiness(business);
            req.setAttribute("businessId", businessId);
            session.setAttribute("business", business);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("businesspage").forward(req, resp);
    }
}

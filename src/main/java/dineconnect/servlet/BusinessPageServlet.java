package dineconnect.servlet;

import dineconnect.dal.*;
import dineconnect.model.*;

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

@WebServlet("/businesspage")
public class BusinessPageServlet extends HttpServlet {
    protected BusinessDao businessDao;
    protected BusinessByAppointmentDao businessByAppointmentDao;
    protected BusinessAttireDao businessAttireDao;
    protected BusinessAlcoholDao businessAlcoholDao;
    protected PromotionDao promotionDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
        businessByAppointmentDao = BusinessByAppointmentDao.getInstance();
        businessAttireDao = BusinessAttireDao.getInstance();
        businessAlcoholDao = BusinessAlcoholDao.getInstance();
        promotionDao = PromotionDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        Business business = null;
        BusinessByAppointment businessByAppointment = null;
        BusinessAttire businessAttire = null;
        BusinessAlcohol businessAlcohol = null;
        List<Promotion> promotionList = new ArrayList<>();

        String businessId = req.getParameter("businessId");
        HttpSession session = req.getSession();

        try {
            business = businessDao.getBusinessByBusinessId(businessId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (business != null) {
            req.setAttribute("business", business);
            session.setAttribute("business", business);
        }

        try {
            businessByAppointment = businessByAppointmentDao.getBusinessByAppointmentByBusinessId(businessId);
            businessAttire = businessAttireDao.getBusinessAttireByBusinessId(businessId);
            businessAlcohol = businessAlcoholDao.getBusinessAlcoholByBusinessId(businessId);
            promotionList = promotionDao.getPromotionsByBusinessId(businessId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        messages.put("previousBusinessId", businessId);

        if (businessByAppointment != null) {
            req.setAttribute("byAppointmentOnly", businessByAppointment.isByAppointmentOnly() ? "Yes" : "No");
        } else {
            req.setAttribute("byAppointmentOnly", "");
        }
        if (businessAttire != null) {
            req.setAttribute("attire", businessAttire.getAttireType().name());
        } else {
            req.setAttribute("attire", "");
        }
        if (businessAlcohol != null) {
            req.setAttribute("alcohol", businessAlcohol.getAlcoholType().name());
        } else {
            req.setAttribute("alcohol", "");
        }

        req.setAttribute("promotionList", promotionList);

        if(business != null) {
            req.getRequestDispatcher("/businessPage.jsp").forward(req, resp);
        }else {
            String errorMessage = "Invalid Business ID. Please try again.";
            messages.put("errorMessage", errorMessage);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

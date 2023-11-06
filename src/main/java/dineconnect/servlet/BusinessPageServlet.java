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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Weixin Liu
 */

@WebServlet("/businesspage")
public class BusinessPageServlet extends HttpServlet {
    protected BusinessDao businessDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        Business business = null;
        String businessId = req.getParameter("businessId");
        HttpSession session = req.getSession();

        try {
            business = businessDao.getBusinessByBusinessId(businessId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        messages.put("previousBusinessId", businessId);

        if (business != null) {
            req.setAttribute("business", business);
            session.setAttribute("business", business);
            req.getRequestDispatcher("/businessPage.jsp").forward(req, resp);
        } else {
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

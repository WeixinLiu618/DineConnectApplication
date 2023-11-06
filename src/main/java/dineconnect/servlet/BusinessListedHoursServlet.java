package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.model.Business;

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
@WebServlet("/businesslistedhours")
public class BusinessListedHoursServlet extends HttpServlet {
    protected BusinessDao businessDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessId = req.getParameter("businessId");
        Business business = null;
        try {
            business = businessDao.getBusinessByBusinessId(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(business != null) {
            req.setAttribute("business", business);
            req.getRequestDispatcher("/businessListedHours.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

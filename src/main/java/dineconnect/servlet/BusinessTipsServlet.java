package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.TipDao;
import dineconnect.model.Business;
import dineconnect.model.Tip;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/businesstips")
public class BusinessTipsServlet extends HttpServlet {
    protected BusinessDao businessDao;
    protected TipDao tipDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
        tipDao = TipDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessId = req.getParameter("businessId");
        Business business = null;
        List<Tip> tipList = new ArrayList<>();
        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            tipList = tipDao.getTipByBusinessId(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (business != null) {
            req.setAttribute("business", business);
            req.setAttribute("tipList", tipList);
            req.getRequestDispatcher("/businessTips.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

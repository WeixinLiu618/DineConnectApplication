package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.TipDao;
import dineconnect.model.Business;
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
        User user = null;
        String businessId = req.getParameter("businessId");
        Business business = null;
        List<Tip> tipList = new ArrayList<>();

        HttpSession session = req.getSession();
        Object userAttribute = session.getAttribute("user");

        if (userAttribute != null) {
            user = (User) userAttribute;
        }
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            tipList = tipDao.getTipByBusinessId(businessId);
            req.setAttribute("user", user);
            req.setAttribute("business", business);
            req.setAttribute("tipList",tipList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/businessTips.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

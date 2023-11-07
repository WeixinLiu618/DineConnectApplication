package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.TipDao;
import dineconnect.dal.UserDao;
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
import java.util.Date;

@WebServlet("/addtip")
public class AddTipServlet extends HttpServlet {
    protected UserDao userDao;
    protected BusinessDao businessDao;
    protected TipDao tipDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
        businessDao = BusinessDao.getInstance();
        tipDao = TipDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        Business business = null;

        String businessId = req.getParameter("businessId");
        String text = req.getParameter("text");
        HttpSession session = req.getSession();
        Object userAttribute = session.getAttribute("user");
        if (userAttribute != null) {
            user = (User) userAttribute;
        }
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        if (text != null && !text.trim().isEmpty()) {
            try {
                business = businessDao.getBusinessByBusinessId(businessId);
                Tip tip = new Tip(text, new Date(), user, business);
                tip = tipDao.create(tip);
                req.setAttribute("user", user);
                req.setAttribute("business", business);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        req.getRequestDispatcher("/businesstips").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

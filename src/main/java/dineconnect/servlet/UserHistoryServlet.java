package dineconnect.servlet;

import dineconnect.dal.UserDao;
import dineconnect.model.User;

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

@WebServlet("/userhistory")
public class UserHistoryServlet extends HttpServlet {

    protected UserDao userDao;
    @Override
    public void init() throws ServletException {
       userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userid");
        User user = null;
        try {
            user = userDao.getUserByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/userHistory.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("/userPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

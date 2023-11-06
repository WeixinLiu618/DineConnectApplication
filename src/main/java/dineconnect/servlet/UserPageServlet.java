package dineconnect.servlet;

import dineconnect.dal.UserDao;
import dineconnect.model.User;

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
@WebServlet("/userpage")
public class UserPageServlet extends HttpServlet {
    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        HttpSession session = req.getSession();
        req.setAttribute("messages", messages);
        User user = null;
        String userId = req.getParameter("userId");

        try {
            user = userDao.getUserByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        // Save the previous search term, so it can be used as the default
        // in the input box when rendering login.jsp.
        messages.put("previousUserId", userId);

        if (user != null) {
            req.setAttribute("user", user);
            session.setAttribute("user", user);
            req.getRequestDispatcher("/userPage.jsp").forward(req, resp);
        } else {
            String errorMessage = "Invalid User ID. Please try again.";
            messages.put("errorMessage", errorMessage);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

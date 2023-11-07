package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.UserDao;
import dineconnect.model.Business;
import dineconnect.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weixin Liu
 */
@WebServlet("/userpage")
public class UserPageServlet extends HttpServlet {
    protected UserDao userDao;
    private BusinessDao businessDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
        businessDao = BusinessDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        User user = null;
        List<Business> businessList = new ArrayList<>();
        req.setAttribute("messages", messages);

        HttpSession session = req.getSession();

        String userId = req.getParameter("userId");
        System.out.println(userId);
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
        }


        BigDecimal longitude = BigDecimal.valueOf(1000000.0);
        BigDecimal latitude = BigDecimal.valueOf(1000000.0);
        String inputLongitude = req.getParameter("longitude");
        String inputLatitude = req.getParameter("latitude");
        if (inputLongitude != null && inputLatitude != null && !inputLongitude.trim().isEmpty() && !inputLatitude.trim().isEmpty()) {
            try {
                longitude = new BigDecimal(inputLongitude);
                latitude = new BigDecimal(inputLatitude);
            } catch (NumberFormatException e) {
                e.printStackTrace();
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric values for latitude or longitude.");
            }
            // -90 to 90 for latitude and -180 to 180 for longitude
            boolean isLatitudeInRange = (latitude.compareTo(new BigDecimal("-90")) >= 0) && (latitude.compareTo(new BigDecimal("90")) <= 0);
            boolean isLongitudeInRange = (longitude.compareTo(new BigDecimal("-180")) >= 0) && (longitude.compareTo(new BigDecimal("180")) <= 0);

            if (isLatitudeInRange && isLongitudeInRange) {
                try {
                    businessList = businessDao.getTopClosestBusiness(longitude, latitude);
//                    businessList.forEach(System.out::println);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        req.setAttribute("businessList", businessList);


        if (user != null) {
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

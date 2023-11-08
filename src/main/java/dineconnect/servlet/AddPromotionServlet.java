package dineconnect.servlet;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.PromotionDao;
import dineconnect.model.Business;
import dineconnect.model.Promotion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Weixin Liu
 */
@WebServlet("/addpromotion")
public class AddPromotionServlet extends HttpServlet {
    protected BusinessDao businessDao;
    protected PromotionDao promotionDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
        promotionDao = PromotionDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Business business = null;
        String businessId = req.getParameter("businessId");
        String startTimeString = req.getParameter("startTime");
        String endTimeString = req.getParameter("endTime");
        String event = req.getParameter("event");
        // get the current business
        try {
            business = businessDao.getBusinessByBusinessId(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // if current business is null, dispatch to login page
        if (business == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        if (startTimeString != null && !startTimeString.trim().isEmpty()
                && endTimeString != null && !endTimeString.trim().isEmpty()
                && event != null && !event.trim().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date startTime = dateFormat.parse(startTimeString);
                Date endTime = dateFormat.parse(endTimeString);
                Promotion promotion = new Promotion(business, startTime, endTime, event);
                promotionDao.create(promotion);
                req.setAttribute("business", business);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        req.getRequestDispatcher("/businesspage").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

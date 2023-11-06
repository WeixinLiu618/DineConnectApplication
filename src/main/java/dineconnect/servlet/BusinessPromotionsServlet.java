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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weixin Liu
 */

@WebServlet("/businesspromotions")
public class BusinessPromotionsServlet extends HttpServlet {
    protected BusinessDao businessDao;
    protected PromotionDao promotionDao;

    @Override
    public void init() throws ServletException {
        businessDao = BusinessDao.getInstance();
        promotionDao = PromotionDao.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessId = req.getParameter("businessId");
        Business business = null;
        List<Promotion> promotionList = new ArrayList<>();
        try {
            business = businessDao.getBusinessByBusinessId(businessId);
            promotionList = promotionDao.getPromotionsByBusinessId(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (business != null) {
            req.setAttribute("business", business);
            req.setAttribute("promotionList", promotionList);
            req.getRequestDispatcher("/businessPromotions.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Weixin Liu
 */
@WebServlet("/promotiondelete")
public class DeletePromotionServlet extends HttpServlet {
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
        int promotionId = Integer.parseInt(req.getParameter("promotionId"));
        HttpSession session = req.getSession();
        Business business = null;
        Object businessAttribute = session.getAttribute("business");
        if (businessAttribute != null) {
            business = (Business) businessAttribute;
        } else {
            try {
                business = businessDao.getBusinessByBusinessId(businessId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        try {
            Promotion promotion = promotionDao.getPromotionByPromotionId(promotionId);
            req.setAttribute("businessId", businessId);
            session.setAttribute("business", business);
            promotionDao.delete(promotion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/businesspage").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);

    }
}

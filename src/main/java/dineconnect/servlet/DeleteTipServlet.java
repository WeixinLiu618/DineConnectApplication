package dineconnect.servlet;

import dineconnect.dal.TipDao;
import dineconnect.dal.UserDao;
import dineconnect.model.Tip;

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
@WebServlet("/tipdelete")
public class DeleteTipServlet extends HttpServlet {
    protected TipDao tipDao;
    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        tipDao = TipDao.getInstance();
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipId = req.getParameter("tipId");
        try {
            Tip toDeleteTip = tipDao.getTipByTipId(Integer.parseInt(tipId));
            if (toDeleteTip != null) {
                String userId = toDeleteTip.getUser().getUserId();
                req.setAttribute("userid", userId);
                tipDao.delete(toDeleteTip);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/userhistory").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

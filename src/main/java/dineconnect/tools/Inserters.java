package dineconnect.tools;

import dineconnect.dal.UserDao;
import dineconnect.model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * @author Weixin Liu
 */
public class Inserters {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = UserDao.getInstance();
        User weixin1 = new User(String.valueOf(UUID.randomUUID()), "weixin1", new Date());
        weixin1 = userDao.create(weixin1);
        System.out.println(userDao.getUserByUserId(weixin1.getUserId()));

    }
}


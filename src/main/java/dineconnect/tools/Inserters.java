package dineconnect.tools;

import dineconnect.dal.BusinessDao;
import dineconnect.dal.ReviewDao;
import dineconnect.dal.UserDao;
import dineconnect.model.Business;
import dineconnect.model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Weixin Liu
 */
public class Inserters {
    public static void main(String[] args) throws SQLException {
//        UserDao userDao = UserDao.getInstance();
//        User weixin1 = new User(String.valueOf(UUID.randomUUID()), "weixin1", new Date());
//        weixin1 = userDao.create(weixin1);


        BusinessDao businessDao = BusinessDao.getInstance();
        String s=  "SELECT b.BusinessId, b.BusinessName, b.BusinessStars, b.Longitude, b.Latitude,b.Address," +
                "b.MondayListedHours,b.TuesdayListedHours,b.WednesdayListedHours,b.ThursdayListedHours," +
                "b.FridayListedHours,b.SaturdayListedHours,b.SundayListedHours,b.PostalCode," +
                "COUNT(r.ReviewId) AS NumberOfReviews, AVG(r.CommentStars) AS AverageRating, " +
                "6371 * acos(cos(radians(?)) * cos(radians(b.Latitude)) * cos(radians(b.Longitude) - radians(?)) " +
                "+ sin(radians(?)) * sin(radians(b.Latitude))) AS distance \n" +
                "FROM businesses b JOIN Reviews r ON b.BusinessId = r.BusinessId\n" +
                "GROUP BY b.BusinessId, b.BusinessName, b.Latitude, b.Longitude\n" +
                "ORDER BY distance ASC, NumberOfReviews DESC, AverageRating DESC\n" +
                "LIMIT 10;";
        System.out.println(businessDao.getRatingForBusiness("Jn4tRtjIuz6MBCykQySpeg"));
    }
}


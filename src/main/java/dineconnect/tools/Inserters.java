package dineconnect.tools;

import dineconnect.dal.*;
import dineconnect.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Weixin Liu
 */
public class Inserters {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = UserDao.getInstance();
        BusinessDao businessDao = BusinessDao.getInstance();
        ReviewDao reviewDao = ReviewDao.getInstance();
        TipDao tipDao = TipDao.getInstance();
        CheckinDao checkinDao = CheckinDao.getInstance();
        PromotionDao promotionDao = PromotionDao.getInstance();



//        User weixin1 = new User(String.valueOf(UUID.randomUUID()), "weixin1", new Date());
        User user = userDao.getUserByUserId("__FzScrH7kzHXdZpS_wjfA");
        Business business = businessDao.getBusinessByBusinessId("_tADqh3OmH6CzGJWKkru3g");
//        Checkin checkin = checkinDao.create(new Checkin(new Date(), user, business));
//        System.out.println(checkin);
        Tip tip = new Tip("Close from 2022-01-01", new Date(), user, business);
        tipDao.create(tip);

        promotionDao.create(new Promotion(business,new Date(),new Date(),"Buy 2 get 1"));


//        CityDao cityDao = CityDao.getInstance();
//        City city = new City("postalCode", "cityName", "state");
//        city = cityDao.create(city);
//
//        BusinessDao businessDao = BusinessDao.getInstance();
//        Business business = new Business("businessId", "businessName", 1.2,
//                new BigDecimal(27.8978517), new BigDecimal(-74.905917), "address", "mondayListedHours", "tuesdayListedHours",
//                "wednesdayListedHours", "thursdayListedHours", "fridayListedHours",
//                "saturdayListedHours", "sundayListedHours", city);
//        business = businessDao.create(business);
//
//        TipDao tipDao = TipDao.getInstance();
//        Tip tip = new Tip("text", new Date(), weixin1, business);
//        tip = tipDao.create(tip);
//
//        Tip tip1 = tipDao.getTipByTipId(1);
//        System.out.format("Reading tip: i:%s tx:%s t:%s u:%s b:%s \n",
//                tip1.getTipId(), tip1.getText(), tip1.getCreatedTime(), tip1.getUser().getUserName(), tip1.getBusiness().getBusinessId());
//
//        List<Tip> tipList1 = tipDao.getTipByBusinessId("businessId");
//        for(Tip t1: tipList1) {
//            System.out.format("Reading tip1: i:%s t:%s u:%s b:%s \n",
//                    t1.getTipId(), t1.getText(), t1.getCreatedTime(), t1.getUser().getUserName(), t1.getBusiness().getBusinessId());
//        }
//
//        List<Tip> tipList2 = tipDao.getTipsByUserId("userId");
//        for(Tip t2: tipList2) {
//            System.out.format("Reading tip2: i:%s t:%s u:%s b:%s \n",
//                    t2.getTipId(), t2.getText(), t2.getCreatedTime(), t2.getUser().getUserName(), t2.getBusiness().getBusinessId());
//        }
//
//        CheckinDao checkinDao = CheckinDao.getInstance();
//        Checkin checkin = new Checkin(new Date(), weixin1, business);
//        checkin = checkinDao.create(checkin);
//
//        Checkin checkin1 = checkinDao.getCheckinByCheckinId(1);
//        System.out.format("Reading checkin: i:%s t:%s u:%s b:%s \n",
//                checkin1.getCheckInId(), checkin1.getCheckInTime(), checkin1.getUser().getUserName(), checkin1.getBusiness().getBusinessId());
//
//        List<Checkin> checkinList1 = checkinDao.getCheckinsByBusinessId("businessId");
//        for(Checkin ch1: checkinList1) {
//            System.out.format("Reading checkin1: i:%s t:%s u:%s b:%s \n",
//                    ch1.getCheckInId(), ch1.getCheckInTime(), ch1.getUser().getUserName(), ch1.getBusiness().getBusinessId());
//        }
//
//        List<Checkin> checkinList2 = checkinDao.getCheckinsByUserId("userId");
//        for(Checkin ch2: checkinList2) {
//            System.out.format("Reading checkin2: i:%s t:%s u:%s b:%s \n",
//                    ch2.getCheckInId(), ch2.getCheckInTime(), ch2.getUser().getUserName(), ch2.getBusiness().getBusinessId());
//        }


//        String s=  "SELECT b.BusinessId, b.BusinessName, b.BusinessStars, b.Longitude, b.Latitude,b.Address," +
//                "b.MondayListedHours,b.TuesdayListedHours,b.WednesdayListedHours,b.ThursdayListedHours," +
//                "b.FridayListedHours,b.SaturdayListedHours,b.SundayListedHours,b.PostalCode," +
//                "COUNT(r.ReviewId) AS NumberOfReviews, AVG(r.CommentStars) AS AverageRating, " +
//                "6371 * acos(cos(radians(?)) * cos(radians(b.Latitude)) * cos(radians(b.Longitude) - radians(?)) " +
//                "+ sin(radians(?)) * sin(radians(b.Latitude))) AS distance \n" +
//                "FROM businesses b JOIN Reviews r ON b.BusinessId = r.BusinessId\n" +
//                "GROUP BY b.BusinessId, b.BusinessName, b.Latitude, b.Longitude\n" +
//                "ORDER BY distance ASC, NumberOfReviews DESC, AverageRating DESC\n" +
//                "LIMIT 10;";
//        System.out.println(businessDao.getRatingForBusiness("Jn4tRtjIuz6MBCykQySpeg"));

//        BigDecimal longitude = new BigDecimal("-123241.32414312");
    }
}

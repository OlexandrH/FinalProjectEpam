package ua.my.oblikchasu;

import ua.my.oblikchasu.db.dao.UsersActivityDAO;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.exception.DBException;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.service.UsersActivityService;

public class Tester {
    private static final Logger logger = Logger.getLogger(Class.class);
    public static void main(String[] args) throws DBException, ServiceException {

        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();
        //System.out.println(usersActivityDAO.findSortedPortion("amount_time", 0, 7, "DESC"));
       // System.out.println(usersActivityDAO.findNumberOfUsersActivitiesByUser(new User(10)));

        //UserDAO userDAO = new UserDAO();
        User user = new User(10);
        System.out.println(new UsersActivityService().getPortionByUser(user,"amount_time", 0, 5, "DESC"));
//        //System.out.println(userDAO.findSortedPortion("role", 0, 5));
//        System.out.println(userDAO.findSorted("id"));
//        //        UsersActivityService uas = new UsersActivityService();
//        ActivityService as = new ActivityService();
//
//        System.out.println(as.getActivitiesByCategory(1));
//
////        UserService us = new UserService();
//        User user = us.getUserByLogin("ipetrenko");
//        Activity act = as.getActivityById(3);
//        UsersActivity usersActivity = new UsersActivity(0, user, act, 3345);
       // uas.addUsersActivity(usersActivity);

//        UsersActivityDAO uad = new UsersActivityDAO();
        //System.out.println(uas.getOneUsersActivities(user));
      //  System.out.println(uas.getUsersActivitiesByActivity(act));

        //        UserService us = new UserService();
//        //System.out.println(us.getUsersByRole(UserRole.USER));
//        User user = us.getUserByLogin("ipetrenko");
//        System.out.println(user);
//        //us.changeUserPassword(user, "8524");
//        us.changeUserName(user, "Petrenko Ivan");
//        System.out.println(user);

        //       UserDAO udao = new UserDAO();
//       ActivityDAO adao = new ActivityDAO();
//        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();
//        User user = udao.findById(2);
//        System.out.println(user);
//        Activity activity = adao.findByName("Swimming");
//        System.out.println(activity);
//        UsersActivity usersActivity = usersActivityDAO.findById(7);
//        usersActivity.setUser(user);
//        usersActivity.setActivity(activity);
//                //new UsersActivity(0,user,activity,134468);
//        System.out.println(usersActivity);
//        //usersActivity.setAmountTime(4648694);
//        //usersActivityDAO.update(usersActivity);
//        //usersActivity = usersActivityDAO.create(usersActivity);
//        System.out.println(usersActivityDAO.delete(usersActivity));

//        ActivityCategoryDAO acd = new ActivityCategoryDAO();
//        ActivityCategory cooking = acd.findByName("Cooking");
//        //acd.create(new ActivityCategory(0, "Cooking"));
//        cooking.setName("Cooking cakes");
//        acd.update(cooking);
//        System.out.println(acd.findAll());
//        //System.out.println(acd.findById(1));
//        //
////        List<User> users = udao.findAll();
//        for(User u: users) {
//            System.out.println(u);
//        }

//        ActivityDAO actDao = new ActivityDAO();
//        List<Activity> acts = actDao.findAll();
//        for(Activity a: acts) {
//            System.out.println(a);
//        }


//        ActivityService as = new ActivityService();
//        System.out.println(as.getAllActivities());

//        ActivityDAO acd = new ActivityDAO();
//        System.out.println(acd.findById(2));
//        Activity act = new Activity(0, "Messing Around", new ActivityCategory(1, null));
//        //acd.create(act);
//        //System.out.println(acd.findByName("Messing Around"));
//        //act = acd.findById(6);
////        act.setName("Doing useful stuff");
////        act.setCategory(new ActivityCategory(1, null));
////        acd.update(act);
////        System.out.println(acd.findById(6));
//            act.setId(13);
//            acd.delete(act);
////        User user = null;
//        try {
//            user = udao.findById(8);
//        } catch (DBException e) {
//            logger.error("Error", e);
//        }
//        System.out.println(user);
//        logger.warn("Warning");
//        logger.error("Error");
//        logger.fatal("Fatal");

//        User user = new User(0, "freeman", "crowbar", UserRole.USER, "Gordon Freeman");
//        user = udao.create(user);
//
//        System.out.println(user);
//
//        System.out.println(udao.delete(user));

        //System.out.println(udao.delete(new User(100, "sfsa", "asfa", UserRole.USER, "adsfasdfasd")));
//        user.setName("Tomcat");
//        System.out.println(user);
//        udao.update(user);
//
//        User user2 = null;
//        try {
//            user2 = udao.findById(8);
//        } catch (DBException e) {
//            logger.error("Error", e);
//        }
//        System.out.println(user2);
    }
}

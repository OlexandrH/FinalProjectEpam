package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.UsersActivityDAO;
import ua.my.oblikchasu.db.entity.*;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.service.exception.ErrorMsg;
import ua.my.oblikchasu.service.exception.ServiceException;

import java.util.LinkedList;
import java.util.List;


public class UsersActivityService {

    private static final Logger logger = Logger.getLogger(UsersActivityService.class);
    UsersActivityDAO usersActivityDAO = new UsersActivityDAO() ;
    UserService userService = new UserService();
    ActivityService activityService = new ActivityService();

    public UsersActivity getById(int id) throws ServiceException {
        UsersActivity usersActivity = null;
        try {
            usersActivity = usersActivityDAO.findById(id);
            if(usersActivity != null) {
                usersActivity.setUser(userService.getById(usersActivity.getUser().getId()));
                usersActivity.setActivity(activityService.getById(usersActivity.getActivity().getId()));
            }
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_DELETE, e);
        }
        return usersActivity;
    }

    public List<UsersActivity> getAll() throws ServiceException {
        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findAll();
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_DELETE, e);
        }
        return usersActivities;
    }

    public List<UsersActivity> getByUser(User user) throws ServiceException {

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findByUser(user);
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_FIND, e);
        }
        return usersActivities;
    }

    public int getCountByUser (User user) throws ServiceException {

       int count = 0;
        try {
            count = usersActivityDAO.findCountByUser(user);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_FIND, e);
        }
        return count;
    }

    public int getCount () throws ServiceException {
        int count = 0;
        try {
            count = usersActivityDAO.findCount();
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_FIND, e);
        }
        return count;
    }

    public int getCountUsersByActivity (Activity activity) throws ServiceException {
        int count = 0;
        try {
            count = usersActivityDAO.findCountUsersByActivity(activity);
        }  catch (DBException e) {
        logger.error(ErrorMsg.ERROR, e);
        throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_FIND, e);
        }
        return count;
    }

    public List<UsersActivity> getPortionByUser(User user, String sortedBy, int from, int amount, String order) throws ServiceException {

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findSortedPortionByUser(user, sortedBy, from, amount, order);
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_FIND, e);
        }
        return usersActivities;
    }

    public List<UsersActivity> getByActivity(Activity activity) throws ServiceException {

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findByActivity(activity);
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_FIND, e);
        }
        return usersActivities;
    }

    public UsersActivity add(UsersActivity activity) throws ServiceException {
        UsersActivity addedUsersActivity = null;
        try {
            addedUsersActivity = usersActivityDAO.create(activity);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_ADD, e);
        }
        return addedUsersActivity;
    }

    public boolean update(UsersActivity usersActivity) throws ServiceException {
        try {
            return usersActivityDAO.update(usersActivity);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_UPDATE, e);
        }
    }

    public boolean delete(UsersActivity usersActivity) throws ServiceException {
        try {
            return usersActivityDAO.delete(usersActivity);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USERS_ACTIVITY_CANNOT_DELETE, e);
        }
    }

}

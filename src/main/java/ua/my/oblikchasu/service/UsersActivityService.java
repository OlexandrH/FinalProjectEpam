package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.UsersActivityDAO;
import ua.my.oblikchasu.db.entity.*;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersActivityService {
    private static final Logger logger = Logger.getLogger(UsersActivityService.class);
    UsersActivityDAO usersActivityDAO = new UsersActivityDAO() ;
    UserService userService = new UserService();
    ActivityService activityService = new ActivityService();

    public UsersActivity getUsersActivityById (int id) throws ServiceException {
        UsersActivity usersActivity = null;
        try {
            usersActivity = usersActivityDAO.findById(id);
            usersActivity.setUser(userService.getUserById(usersActivity.getUser().getId()));
            usersActivity.setActivity(activityService.getActivityById(usersActivity.getActivity().getId()));
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user's activity", e);
        }
        return usersActivity;
    }

    public List<UsersActivity> getAllUsersActivities () throws ServiceException {
        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findAll();
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getUserById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getActivityById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user's activity", e);
        }
        return usersActivities;
    }

    public List<UsersActivity> getOneUsersActivities (User user) throws ServiceException {

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findByUser(user);
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getUserById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getActivityById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user's activity", e);
        }
        return usersActivities;
    }

    public List<UsersActivity> getUsersActivitiesPortionByUser (User user, String sortedBy, int from, int amount, String order) throws ServiceException {

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findSortedPortionByUser(user, sortedBy, from, amount, order);
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getUserById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getActivityById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user's activity", e);
        }
        return usersActivities;
    }

    public List<UsersActivity> getUsersActivitiesByActivity (Activity activity) throws ServiceException {

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            usersActivities = usersActivityDAO.findByActivity(activity);
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getUserById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getActivityById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user's activity", e);
        }
        return usersActivities;
    }

    public List<UsersActivity> getUsersActivitiesByStatus (UsersActivityStatus status) throws ServiceException {

        List<UsersActivity> usersActivities = null;
        try {
            usersActivities = usersActivityDAO.findAll();
            usersActivities = usersActivities.stream()
                    .filter((usersActivity) -> usersActivity.getStatus() == status)
                    .collect(Collectors.toList());
            for(UsersActivity userAct: usersActivities) {
                userAct.setUser(userService.getUserById(userAct.getUser().getId()));
                userAct.setActivity(activityService.getActivityById(userAct.getActivity().getId()));
            }

        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user's activity", e);
        }
        return usersActivities;
    }

    public UsersActivity addUsersActivity (UsersActivity activity) throws ServiceException {
        UsersActivity addedUsersActivity = null;
        try {
            addedUsersActivity = usersActivityDAO.create(activity);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot add user's activity", e);
        }
        return addedUsersActivity;
    }

    public boolean updateUsersActivity (UsersActivity usersActivity) throws ServiceException {
        try {
            return usersActivityDAO.update(usersActivity);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot update user's activity", e);
        }
    }

    public boolean deleteUsersActivity (UsersActivity usersActivity) throws ServiceException {
        try {
            return usersActivityDAO.delete(usersActivity);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot delete user's activity", e);
        }
    }

}

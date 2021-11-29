package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.dao.ActivityDAO;
import ua.my.oblikchasu.db.dao.UsersActivityDAO;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.util.LogMsg;

import java.util.LinkedList;
import java.util.List;

public class ActivityService {
    private static final Logger logger = Logger.getLogger(ActivityService.class);

    ActivityDAO activityDAO = new ActivityDAO();

    public Activity getById(int id) throws ServiceException {
        Activity activity = null;
        try {
            activity = activityDAO.findById(id);
            ActivityCategory activityCategory = new ActivityCategoryDAO().findById(activity.getCategory().getId());
            activity.setCategory(activityCategory);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR , e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
        return activity;
    }

    public Activity getByName(String name) throws ServiceException {
        Activity activity = null;
        try {
            activity = activityDAO.findByName(name);
            if(activity != null) {
                ActivityCategory activityCategory = new ActivityCategoryDAO().findById(activity.getCategory().getId());
                activity.setCategory(activityCategory);
            }
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
        return activity;
    }

    public List<Activity> getAll() throws ServiceException {
        List<Activity> activities = new LinkedList<>();
        try {
            activities = activityDAO.findAll();

            ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
            for (Activity a : activities) {
                a.setCategory(activityCategoryDAO.findById(a.getCategory().getId()));
            }
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
        return activities;
    }

    public List<Activity> getSortedPortion (String sortBy, int from, int amount, String order) throws ServiceException {
        List<Activity> activities = new LinkedList<>();
        try {
            activities = activityDAO.findSortedPortion(sortBy, from ,amount, order);
            ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
            UsersActivityDAO usersActivityDAO = new UsersActivityDAO();
            for(Activity a: activities) {
                a.setCategory(activityCategoryDAO.findById(a.getCategory().getId()));
                a.setUserCount(usersActivityDAO.findCountUsersByActivity(a));
                a.setTotalTime(usersActivityDAO.findTotalTimeByActivity(a));
            }
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
            return activities;
    }
    public int getCount () throws ServiceException {
        int count = 0;
        try {
            count = activityDAO.findCount();
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
        return count;
    }



    public List<Activity> getByCategory(int categoryId) throws ServiceException {
        List<Activity> activities = null;
        try {
            activities = activityDAO.findByCategory(categoryId);
            ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
            ActivityCategory activityCategory = activityCategoryDAO.findById(categoryId);

            for (Activity a : activities) {
                a.setCategory(activityCategory);
            }
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
        return activities;
    }

    public Activity add(Activity activity) throws ServiceException {
        Activity addedActivity = null;
        try {
            addedActivity = activityDAO.create(activity);
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_ADD, e);
        }
        return addedActivity;
    }

    public boolean update(Activity activity) throws ServiceException {
        try {
            return activityDAO.update(activity);
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_UPDATE, e);
        }
    }

    public boolean delete(Activity activity) throws ServiceException {
        try {
            return activityDAO.delete(activity);
        } catch (DBException e) {
            logger.error(LogMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_DELETE, e);
        }
    }
}

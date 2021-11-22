package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.dao.ActivityDAO;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.List;

public class ActivityService {
    private static final Logger logger = Logger.getLogger(ActivityService.class);
    ActivityDAO activityDAO = new ActivityDAO() ;

    public Activity getActivityById (int id) throws ServiceException {
        Activity activity = null;
        try {
            activity = activityDAO.findById(id);
            ActivityCategory activityCategory = new ActivityCategoryDAO().findById(activity.getCategory().getId());
            activity.setCategory(activityCategory);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity", e);
        }
        return activity;
    }

    public Activity getActivityByName (String name) throws ServiceException {
        Activity activity = null;
        try {
            activity = activityDAO.findByName(name);
            if(activity != null) {
                ActivityCategory activityCategory = new ActivityCategoryDAO().findById(activity.getCategory().getId());
                activity.setCategory(activityCategory);
            }
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity", e);
        }
        return activity;
    }

    public List<Activity> getAllActivities () throws ServiceException {
        List<Activity> activities = null;
        try {
            activities = activityDAO.findAll();

            ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
            for (Activity a : activities) {
                a.setCategory(activityCategoryDAO.findById(a.getCategory().getId()));
            }
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity", e);
        }
        return activities;
    }

    public List<Activity> getActivitiesByCategory (int categoryId) throws ServiceException {
        List<Activity> activities = null;
        try {
            activities = activityDAO.findByCategory(categoryId);
            ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
            ActivityCategory activityCategory = activityCategoryDAO.findById(categoryId);

            for (Activity a : activities) {
                a.setCategory(activityCategory);
            }
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity", e);
        }
        return activities;
    }

    public Activity addActivity (Activity activity) throws ServiceException {
        Activity addedActivity = null;
        try {
            addedActivity = activityDAO.create(activity);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot add activity", e);
        }
        return addedActivity;
    }

    public boolean updateActivity (Activity activity) throws ServiceException {
        try {
            return activityDAO.update(activity);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot update activity", e);
        }
    }

    public boolean deleteActivity (Activity activity) throws ServiceException {
        try {
            return activityDAO.delete(activity);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot delete activity", e);
        }
    }
}

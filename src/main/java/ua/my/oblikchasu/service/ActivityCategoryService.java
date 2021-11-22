package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.List;

public class ActivityCategoryService {
    private static final Logger logger = Logger.getLogger(ActivityCategoryService.class);
    ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();

    public ActivityCategory getActivityCategoryById (int id) throws ServiceException {
        ActivityCategory activityCategory = null;
        try {
            activityCategory = activityCategoryDAO.findById(id);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity category", e);
        }
        return activityCategory;
    }

    public ActivityCategory getActivityCategoryByName (String name) throws ServiceException {
        ActivityCategory activityCategory = null;
        try {
            activityCategory = activityCategoryDAO.findByName(name);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity category", e);
        }
        return activityCategory;
    }

    public List<ActivityCategory> getAllActivityCategories () throws ServiceException {
        List<ActivityCategory> activityCategories = null;
        try {
            activityCategories = activityCategoryDAO.findAll();
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find activity category", e);
        }
        return activityCategories;
    }

    public ActivityCategory addActivityCategory (ActivityCategory activityCategory) throws ServiceException {
        ActivityCategory addedActivityCategory = null;
        try {
            addedActivityCategory = activityCategoryDAO.create(activityCategory);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot add activity category", e);
        }
        return addedActivityCategory;
    }

    public boolean updateActivityCategory (ActivityCategory activityCategory) throws ServiceException {
        try {
            return activityCategoryDAO.update(activityCategory);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot update activity category", e);
        }
    }

    public boolean deleteActivityCategory (ActivityCategory activityCategory) throws ServiceException {
        try {
            return activityCategoryDAO.delete(activityCategory);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot delete activity category", e);
        }
    }
}

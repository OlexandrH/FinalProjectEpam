package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.service.exception.ErrorMsg;
import ua.my.oblikchasu.service.exception.ServiceException;

import java.util.LinkedList;
import java.util.List;

public class ActivityCategoryService {
    private static final Logger logger = Logger.getLogger(ActivityCategoryService.class);
    ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();

    public ActivityCategory getById(int id) throws ServiceException {
        ActivityCategory activityCategory = null;
        try {
            activityCategory = activityCategoryDAO.findById(id);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_FIND, e);
        }
        return activityCategory;
    }

    public ActivityCategory getByName(String name) throws ServiceException {
        ActivityCategory activityCategory = null;
        try {
            activityCategory = activityCategoryDAO.findByName(name);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_FIND, e);
        }
        return activityCategory;
    }

    public List<ActivityCategory> getAll() throws ServiceException {
        List<ActivityCategory> activityCategories = null;
        try {
            activityCategories = activityCategoryDAO.findAll();
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_FIND, e);
        }
        return activityCategories;
    }

    public List<ActivityCategory> getSortedPortion(String sortBy, int from, int amount, String order) throws ServiceException {
        List<ActivityCategory> activityCategories = new LinkedList<>();
        try {
            activityCategories = activityCategoryDAO.findSortedPortion(sortBy, from, amount, order);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_FIND, e);
        }
        return activityCategories;
    }


    public int getCount () throws ServiceException {
        int count = 0;
        try {
            count = activityCategoryDAO.findCount();
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_FIND, e);
        }
        return count;
    }

    public ActivityCategory add(ActivityCategory activityCategory) throws ServiceException {
        ActivityCategory addedActivityCategory = null;
        try {
            addedActivityCategory = activityCategoryDAO.create(activityCategory);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_ADD, e);
        }
        return addedActivityCategory;
    }

    public boolean update(ActivityCategory activityCategory) throws ServiceException {
        try {
            return activityCategoryDAO.update(activityCategory);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_UPDATE, e);
        }
    }

    public boolean delete(ActivityCategory activityCategory) throws ServiceException {
        try {
            return activityCategoryDAO.delete(activityCategory);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CATEGORY_CANNOT_DELETE, e);
        }
    }
}

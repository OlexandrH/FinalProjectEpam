package ua.my.oblikchasu.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.dao.ActivityDAO;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.service.exception.ServiceException;

public class TestActivityService {

    @Before
    public void createTestEntities() throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        activityCategoryDAO.create(new ActivityCategory(0, "TestCategory"));
    }

    @After
    public void deleteTestEntities () throws DBException, ServiceException {
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.getByName("TestActivity");
        if(testActivity != null) {
            activityService.delete(testActivity);
        }
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        activityCategoryDAO.delete(activityCategoryDAO.findByName("TestCategory"));
    }

    @Test
    public void shouldGetActivityWithValidFields() throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        int activityCategoryId = testActivityCategory.getId();
        ActivityDAO activityDAO = new ActivityDAO();
        Activity testActivity = activityDAO.create(new Activity(0,"TestActivity", new ActivityCategory(activityCategoryId, "")));
        ActivityService activityService = new ActivityService();
        testActivity = activityService.getById(testActivity.getId());
        assertEquals(testActivityCategory, testActivity.getCategory());
        assertEquals(testActivityCategory.getName(), testActivity.getCategory().getName());
        activityService.delete(testActivity);
    }

    @Test
    public void shouldReturnFalseIfActivityUpdateFail() throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.add(new Activity(0, "TestActivity", testActivityCategory));

        activityService.delete(testActivity);
        assertFalse(activityService.update(testActivity));
    }

    @Test
    public void shouldReturnTrueIfActivityUpdateSuccessful() throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.add(new Activity(0, "TestActivity", testActivityCategory));
        assertTrue(activityService.update(testActivity));
        activityService.delete(testActivity);
    }

    @Test
    public void shouldReturnFalseIfActivityDeleteFail() throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.add(new Activity(0, "TestActivity", testActivityCategory));
        activityService.delete(testActivity);
        assertFalse(activityService.delete(testActivity));
    }

    @Test
    public void shouldReturnTrueIfActivityDeleteSuccessful() throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.add(new Activity(0, "TestActivity", testActivityCategory));
        assertTrue(activityService.delete(testActivity));
    }

    @Test
    public void shouldReturnNullIfIdDoesNotExist() throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.add(new Activity(0, "TestActivity", testActivityCategory));
        activityService.delete(testActivity);
        assertNull(activityService.getById(testActivity.getId()));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionIfActivityExists () throws DBException, ServiceException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("TestCategory");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.add(new Activity(0, "TestActivity", testActivityCategory));
        activityService.add(testActivity);
    }
}

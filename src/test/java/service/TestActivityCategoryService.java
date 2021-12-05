package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.service.ActivityCategoryService;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.service.exception.ServiceException;

import static org.junit.Assert.*;

public class TestActivityCategoryService {

    @Before
    public void createTestEntities () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        if(testActivityCategory == null) {
            activityCategoryService.add(new ActivityCategory(0, "Test"));
        }
    }

    @After
    public void deleteTestEntities () throws ServiceException {
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.getByName("TestActivity");
        if(testActivity != null) {
            activityService.delete(testActivity);
        }

        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        if(testActivityCategory != null) {
            activityCategoryService.delete(testActivityCategory);
        }

    }

    @Test
    public void shouldReturnFalseIfUpdateCategoryFail () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        activityCategoryService.delete(testActivityCategory);
        testActivityCategory.setName("Test2");
        assertFalse(activityCategoryService.update(testActivityCategory));
    }

    @Test
    public void shouldReturnTrueIfUpdateCategorySuccessful () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        testActivityCategory.setName("Test2");
        assertTrue(activityCategoryService.update(testActivityCategory));
        activityCategoryService.delete(testActivityCategory);
    }

    @Test
    public void shouldReturnFalseIfDeleteCategoryFail () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        activityCategoryService.delete(testActivityCategory);
        assertFalse(activityCategoryService.delete(testActivityCategory));
    }

    @Test
    public void shouldReturnTrueIfDeleteCategorySuccessful () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        assertTrue(activityCategoryService.delete(testActivityCategory));
    }

    @Test
    public void shouldReturnNullIfIdDoesNotExist () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        activityCategoryService.delete(testActivityCategory);
        assertNull( activityCategoryService.getById(testActivityCategory.getId()));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionIfCategoryExists () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        activityCategoryService.add(new ActivityCategory(0, "Test"));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionIfTryToDeleteCategoryWhichHasActivities () throws ServiceException {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityCategory testActivityCategory = activityCategoryService.getByName("Test");
        ActivityService activityService = new ActivityService();
        Activity testActivity = activityService.getByName("TestActivity");
        if(testActivity != null) {
            activityService.delete(testActivity);
        }
        activityService.add(new Activity(0, "TestActivity", testActivityCategory));
        activityCategoryService.delete(testActivityCategory);
    }
}

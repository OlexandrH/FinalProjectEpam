package ua.my.oblikchasu.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.dao.ActivityDAO;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.List;

import static org.junit.Assert.*;

public class TestActivityDAO {

    @Before
    public void addTestCategory() throws DBException {

        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        if(activityCategoryDAO.findByName("TestCategory") == null) {
            activityCategoryDAO.create(new ActivityCategory(0, "TestCategory"));
        }
    }

    @After
    public void deleteTestEntities () throws DBException {
        ActivityDAO activityDAO = new ActivityDAO();
        Activity testActivity = activityDAO.findByName("Test");
        if(testActivity != null) {
            activityDAO.delete(testActivity);
        }
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        activityCategoryDAO.delete(testCategory);
    }

    @Test
    public void shouldAddActivity () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        testActivity = activityDAO.create(testActivity);
        assertNotNull(activityDAO.findById(testActivity.getId()));
    }

    @Test
    public void shouldFindCountActivities () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        int activityCount = activityDAO.findCount();
        activityDAO.create(testActivity);
        int activityCountAfterAddingActivity = activityDAO.findCount();
        assertEquals(1, activityCountAfterAddingActivity - activityCount);
    }

    @Test
    public void shouldFindAllActivities () throws DBException {
        ActivityDAO activityDAO = new ActivityDAO();
        List<Activity> allActivities = activityDAO.findAll();
        int activityCount = activityDAO.findCount();
        assertEquals(activityCount, allActivities.size());
    }

    @Test
    public void shouldUpdateActivity () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        testActivity = activityDAO.create(testActivity);
        testActivity.setName("Test2");
        activityDAO.update(testActivity);
        assertEquals(testActivity, activityDAO.findByName(testActivity.getName()));
        activityDAO.delete(testActivity);
    }

    @Test
    public void shouldDeleteActivity () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        testActivity = activityDAO.create(testActivity);
        activityDAO.delete(testActivity);
        assertNull(activityDAO.findByName(testActivity.getName()));
    }

    @Test
    public void shouldFindActivitiesByCategory () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        Activity testActivity2 = new Activity(0, "Test2", testCategory);
        testActivity = activityDAO.create(testActivity);
        testActivity2 = activityDAO.create(testActivity2);
        List<Activity> activitiesOfCategory = activityDAO.findByCategory(testCategory.getId());
        assertEquals(2, activitiesOfCategory.size());
        assertTrue(activitiesOfCategory.contains(testActivity));
        assertTrue(activitiesOfCategory.contains(testActivity2));
        activityDAO.delete(testActivity2);
    }

    @Test
    public void shouldFindActivityByName () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        testActivity = activityDAO.create(testActivity);
        assertEquals(testActivity, activityDAO.findByName("Test"));
    }

    @Test
    public void shouldFindActivityById () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");
        Activity testActivity = new Activity(0, "Test", testCategory);
        testActivity = activityDAO.create(testActivity);
        assertEquals(testActivity, activityDAO.findById(testActivity.getId()));
    }

    @Test
    public void shouldFindSortedPortionById () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");

        Activity testActivityA = activityDAO.create(new Activity(0, "000TestA", testCategory));
        Activity testActivityB = activityDAO.create(new Activity(0, "000TestB", testCategory));
        Activity testActivityC = activityDAO.create(new Activity(0, "000TestC", testCategory));

        List<Activity> activities = activityDAO.findSortedPortion("id", 0, 3, "desc");

        assertEquals(3, activities.size());
        assertEquals(testActivityA, activities.get(2));
        assertEquals(testActivityB, activities.get(1));
        assertEquals(testActivityC, activities.get(0));

        activityDAO.delete(testActivityA);
        activityDAO.delete(testActivityB);
        activityDAO.delete(testActivityC);
    }

    @Test
    public void shouldFindSortedPortionByName () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.findByName("TestCategory");

        Activity testActivityA = activityDAO.create(new Activity(0, "000TestA", testCategory));
        Activity testActivityB = activityDAO.create(new Activity(0, "000TestB", testCategory));
        Activity testActivityC = activityDAO.create(new Activity(0, "000TestC", testCategory));

        List<Activity> activities = activityDAO.findSortedPortion("name", 0, 3, "asc");

        assertEquals(3, activities.size());
        assertEquals(testActivityA, activities.get(0));
        assertEquals(testActivityB, activities.get(1));
        assertEquals(testActivityC, activities.get(2));

        activityDAO.delete(testActivityA);
        activityDAO.delete(testActivityB);
        activityDAO.delete(testActivityC);
    }

    @Test
    public void shouldFindSortedPortionByCategory () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategory testCategory = activityCategoryDAO.create(new ActivityCategory(0,"000TestCategory"));

        Activity testActivityA = activityDAO.create(new Activity(0, "000TestA", testCategory));
        Activity testActivityB = activityDAO.create(new Activity(0, "000TestB", testCategory));
        Activity testActivityC = activityDAO.create(new Activity(0, "000TestC", testCategory));

        List<Activity> activities = activityDAO.findSortedPortion("category", 0, 3, "asc");

        assertEquals(testActivityA, activities.get(0));
        assertEquals(testActivityB, activities.get(1));
        assertEquals(testActivityC, activities.get(2));

        activityDAO.delete(testActivityA);
        activityDAO.delete(testActivityB);
        activityDAO.delete(testActivityC);
        activityCategoryDAO.delete(testCategory);
    }
}

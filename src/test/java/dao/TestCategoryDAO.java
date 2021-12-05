package dao;

import org.junit.After;
import org.junit.Test;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.List;

import static org.junit.Assert.*;

public class TestCategoryDAO {

    @After
    public void deleteTestCategory () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.findByName("Test");
        if(testActivityCategory != null) {
            activityCategoryDAO.delete(testActivityCategory);
        }
    }

    @Test
    public void shouldAddCategory () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.create(new ActivityCategory(0, "Test"));
        assertEquals(testActivityCategory, activityCategoryDAO.findByName("Test"));
    }

    @Test
    public void shouldUpdateCategory () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.create(new ActivityCategory(0, "Test"));
        testActivityCategory.setName("Test2");
        activityCategoryDAO.update(testActivityCategory);
        assertEquals(testActivityCategory.getName(), activityCategoryDAO.findById(testActivityCategory.getId()).getName());
        activityCategoryDAO.delete(testActivityCategory);
    }

    @Test
    public void shouldDeleteCategory () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.create(new ActivityCategory(0, "Test"));
        activityCategoryDAO.delete(testActivityCategory);
        assertNull(activityCategoryDAO.findById(testActivityCategory.getId()));
    }

    @Test
    public void shouldFindCountCategories () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        int categoryCount = activityCategoryDAO.findCount();
        int categoryCountAfterAddingCategory = activityCategoryDAO.findCount();
        assertEquals(1, categoryCountAfterAddingCategory - categoryCount);
    }

    @Test
    public void shouldFindAllCategories () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        int categoryCount = activityCategoryDAO.findCount();
        List<ActivityCategory> allActivityCategories = activityCategoryDAO.findAll();
        assertEquals(categoryCount, allActivityCategories.size());
    }

    @Test
    public void shouldFindById () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.create(new ActivityCategory(0, "Test"));
        assertEquals(testActivityCategory, activityCategoryDAO.findById(testActivityCategory.getId()));
    }

    @Test
    public void shouldFindByName () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testActivityCategory = activityCategoryDAO.create(new ActivityCategory(0, "Test"));
        assertEquals(testActivityCategory, activityCategoryDAO.findByName("Test"));
    }

    @Test
    public void shouldFindSortedPortionById () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();

        ActivityCategory testActivityCategoryA = activityCategoryDAO.create(new ActivityCategory(0, "000TestA"));
        ActivityCategory testActivityCategoryB = activityCategoryDAO.create(new ActivityCategory(0, "000TestB"));
        ActivityCategory testActivityCategoryC = activityCategoryDAO.create(new ActivityCategory(0, "000TestC"));

        List<ActivityCategory> categories = activityCategoryDAO.findSortedPortion("id", 0, 3, "desc");
        assertEquals(testActivityCategoryA, categories.get(2));
        assertEquals(testActivityCategoryB, categories.get(1));
        assertEquals(testActivityCategoryC, categories.get(0));

        activityCategoryDAO.delete(testActivityCategoryA);
        activityCategoryDAO.delete(testActivityCategoryB);
        activityCategoryDAO.delete(testActivityCategoryC);
    }

    @Test
    public void shouldFindSortedPortionByName () throws DBException {
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();

        ActivityCategory testActivityCategoryA = activityCategoryDAO.create(new ActivityCategory(0, "000TestA"));
        ActivityCategory testActivityCategoryB = activityCategoryDAO.create(new ActivityCategory(0, "000TestB"));
        ActivityCategory testActivityCategoryC = activityCategoryDAO.create(new ActivityCategory(0, "000TestC"));

        List<ActivityCategory> categories = activityCategoryDAO.findSortedPortion("name", 0, 3, "asc");
        assertEquals(testActivityCategoryA, categories.get(0));
        assertEquals(testActivityCategoryB, categories.get(1));
        assertEquals(testActivityCategoryC, categories.get(2));

        activityCategoryDAO.delete(testActivityCategoryA);
        activityCategoryDAO.delete(testActivityCategoryB);
        activityCategoryDAO.delete(testActivityCategoryC);
    }
}

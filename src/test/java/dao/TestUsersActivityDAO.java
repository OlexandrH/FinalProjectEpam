package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.dao.ActivityDAO;
import ua.my.oblikchasu.db.dao.UserDAO;
import ua.my.oblikchasu.db.dao.UsersActivityDAO;
import ua.my.oblikchasu.db.entity.*;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.List;

import static org.junit.Assert.*;

public class TestUsersActivityDAO {

    @Before
    public void addTestUsers () throws DBException {
        UserDAO userDAO = new UserDAO();
        userDAO.create(new User(0, "testUser1", "test", UserRole.USER, "TestUser1" ));
        userDAO.create(new User(0, "testUser2", "test", UserRole.USER, "TestUser2" ));
    }

    @Before
    public void addTestActivities () throws DBException {

        ActivityDAO activityDAO = new ActivityDAO();
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        ActivityCategory testCategory = activityCategoryDAO.create(new ActivityCategory(0, "TestCategory"));
        activityDAO.create(new Activity(0,"TestActivity1", testCategory));
        activityDAO.create(new Activity(0,"TestActivity2", testCategory));
    }

    @After
    public void deleteTestEntities () throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = userDAO.findByLogin("testUser1");
        if(testUser != null) {
            userDAO.delete(testUser);
        }

        testUser = userDAO.findByLogin("testUser2");
        if(testUser != null) {
            userDAO.delete(testUser);
        }

        ActivityDAO activityDAO = new ActivityDAO();
        activityDAO.delete(activityDAO.findByName("TestActivity1"));
        activityDAO.delete(activityDAO.findByName("TestActivity2"));
        ActivityCategoryDAO activityCategoryDAO = new ActivityCategoryDAO();
        activityCategoryDAO.delete(activityCategoryDAO.findByName("TestCategory"));

    }

    @Test
    public void shouldAddUsersActivity () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity = activityDAO.findByName("TestActivity1");
        UsersActivity testUsersActivity = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        assertNotNull(usersActivityDAO.findById(testUsersActivity.getId()));

        usersActivityDAO.delete(testUsersActivity);
    }

    @Test
    public void shouldFindUsersActivitiesCount () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();
        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity = activityDAO.findByName("TestActivity1");

        int usersActivitiesCount = usersActivityDAO.findCount();
        UsersActivity testUsersActivity = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        assertNotNull(usersActivityDAO.findById(testUsersActivity.getId()));
        int usersActivitiesCountAfterAdding = usersActivityDAO.findCount();
        assertEquals(1, usersActivitiesCountAfterAdding - usersActivitiesCount);

        usersActivityDAO.delete(testUsersActivity);
    }

    @Test
    public void shouldUpdateUsersActivity () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity = activityDAO.findByName("TestActivity1");
        UsersActivity testUsersActivity = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        testUsersActivity.setStatus(UsersActivityStatus.ACCEPTED);
        usersActivityDAO.update(testUsersActivity);
        assertEquals(testUsersActivity.getStatus(), usersActivityDAO.findById(testUsersActivity.getId()).getStatus());

        usersActivityDAO.delete(testUsersActivity);
    }

    @Test
    public void shouldDeleteUsersActivity () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity = activityDAO.findByName("TestActivity1");
        UsersActivity testUsersActivity = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));

        usersActivityDAO.delete(testUsersActivity);
        assertNull(usersActivityDAO.findById(testUsersActivity.getId()));
    }

    @Test
    public void shouldFindAllUsersActivities () throws DBException {
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();
        int usersActivityCount = usersActivityDAO.findCount();
        List<UsersActivity> allUsersActivities = usersActivityDAO.findAll();
        assertEquals(usersActivityCount, allUsersActivities.size());
    }

    @Test
    public void shouldFindCountByUser () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");
        Activity testActivity2 = activityDAO.findByName("TestActivity2");
        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity1, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity2, 1, UsersActivityStatus.BOOKED));

        assertEquals(2, usersActivityDAO.findCountByUser(testUser));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test
    public void shouldReturnZeroCountByUserIfUserDoesNotExist () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");
        Activity testActivity2 = activityDAO.findByName("TestActivity2");
        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity1, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity2, 1, UsersActivityStatus.BOOKED));
        userDAO.delete(testUser);
        assertEquals(0, usersActivityDAO.findCountByUser(testUser));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }


    @Test
    public void shouldFindCountUsersByActivity () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser1 = userDAO.findByLogin("testUser1");
        User testUser2 = userDAO.findByLogin("testUser2");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");

        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser1, testActivity1, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser2, testActivity1, 1, UsersActivityStatus.BOOKED));

        assertEquals(2, usersActivityDAO.findCountUsersByActivity(testActivity1));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test
    public void shouldFindTotalTimeByActivity () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser1 = userDAO.findByLogin("testUser1");
        User testUser2 = userDAO.findByLogin("testUser2");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");

        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser1, testActivity1, 10, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser2, testActivity1, 20, UsersActivityStatus.BOOKED));

        assertEquals(30, usersActivityDAO.findTotalTimeByActivity(testActivity1));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test
    public void shouldFindUsersActivityById () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity = activityDAO.findByName("TestActivity1");
        UsersActivity testUsersActivity = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));

        assertEquals(testUsersActivity, usersActivityDAO.findById(testUsersActivity.getId()));

        usersActivityDAO.delete(testUsersActivity);
    }

    @Test
    public void shouldFindUsersActivitiesByUser () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser = userDAO.findByLogin("testUser1");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");
        Activity testActivity2 = activityDAO.findByName("TestActivity2");
        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity1, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser, testActivity2, 1, UsersActivityStatus.BOOKED));

        List<UsersActivity> usersActivities = usersActivityDAO.findByUser(testUser);
        assertEquals(2, usersActivities.size());
        assertTrue(usersActivities.contains(testUsersActivity1));
        assertTrue(usersActivities.contains(testUsersActivity2));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test
    public void shouldFindUsersActivitiesByActivity () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser1 = userDAO.findByLogin("testUser1");
        User testUser2 = userDAO.findByLogin("testUser2");
        Activity testActivity = activityDAO.findByName("TestActivity1");

        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser1, testActivity, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser2, testActivity, 1, UsersActivityStatus.BOOKED));

        List<UsersActivity> usersActivities = usersActivityDAO.findByActivity(testActivity);
        assertEquals(2, usersActivities.size());
        assertTrue(usersActivities.contains(testUsersActivity1));
        assertTrue(usersActivities.contains(testUsersActivity2));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test
    public void shouldFindSortedPortionById () throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser1 = userDAO.findByLogin("testUser1");
        User testUser2 = userDAO.findByLogin("testUser2");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");
        Activity testActivity2 = activityDAO.findByName("TestActivity2");

        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser1, testActivity1, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser2, testActivity2, 1, UsersActivityStatus.BOOKED));

        List<UsersActivity> usersActivities = usersActivityDAO.findSortedPortion("id", 0, 2, "desc");

        assertEquals(testUsersActivity1, usersActivities.get(1));
        assertEquals(testUsersActivity2, usersActivities.get(0));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test
    public void shouldFindSortedByIdPortionByUser() throws DBException {
        UserDAO userDAO = new UserDAO();
        ActivityDAO activityDAO = new ActivityDAO();
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();

        User testUser1 = userDAO.findByLogin("testUser1");
        Activity testActivity1 = activityDAO.findByName("TestActivity1");
        Activity testActivity2 = activityDAO.findByName("TestActivity2");

        UsersActivity testUsersActivity1 = usersActivityDAO.create(new UsersActivity(0, testUser1, testActivity1, 1, UsersActivityStatus.BOOKED));
        UsersActivity testUsersActivity2 = usersActivityDAO.create(new UsersActivity(0, testUser1, testActivity2, 1, UsersActivityStatus.BOOKED));

        List<UsersActivity> usersActivities = usersActivityDAO.findSortedPortion("id", 0, 2, "desc");

        assertEquals(testUsersActivity1, usersActivities.get(1));
        assertEquals(testUsersActivity2, usersActivities.get(0));

        usersActivityDAO.delete(testUsersActivity1);
        usersActivityDAO.delete(testUsersActivity2);
    }

    @Test(expected = UnsupportedOperationException.class )
    public void shouldThrowUnsupportedOperationException () {
        UsersActivityDAO usersActivityDAO = new UsersActivityDAO();
        usersActivityDAO.findByName("test");
    }
}

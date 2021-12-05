package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.my.oblikchasu.db.entity.*;
import ua.my.oblikchasu.service.ActivityCategoryService;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.service.UsersActivityService;
import ua.my.oblikchasu.service.exception.ServiceException;

import java.util.List;

public class TestUsersActivityService {

    @Before
    public void createTestEntities () throws ServiceException {
        UserService userService = new UserService();
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityService activityService = new ActivityService();

        userService.add(new User(0, "testUser", "test", UserRole.USER, "TestUser"));
        ActivityCategory testActivityCategory = activityCategoryService.add(new ActivityCategory(0, "TestCategory"));
        activityService.add(new Activity(0, "TestActivity", testActivityCategory));
    }

    @After
    public void deleteTestEntities () throws ServiceException {
        UserService userService = new UserService();
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        ActivityService activityService = new ActivityService();

        Activity testActivity = activityService.getByName("TestActivity");
        if(testActivity != null) {
            activityService.delete(testActivity);
        }

        ActivityCategory testActivityCategory = activityCategoryService.getByName("TestCategory");
        if(testActivityCategory != null) {
            activityCategoryService.delete(testActivityCategory);
        }

        User testUser = userService.getByLogin("testUser");
        if(testUser != null) {
            userService.delete(testUser);
        }
    }

    @Test
    public void shouldReturnFalseIfUpdateFail () throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        usersActivityService.delete(testUsersActivity);
        testUsersActivity.setStatus(UsersActivityStatus.ACCEPTED);
        assertFalse(usersActivityService.update(testUsersActivity));
    }

    @Test
    public void shouldReturnTrueIfUpdateSuccessful () throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        testUsersActivity.setStatus(UsersActivityStatus.ACCEPTED);
        assertTrue(usersActivityService.update(testUsersActivity));
        usersActivityService.delete(testUsersActivity);
    }

    @Test
    public void shouldReturnFalseIfDeleteFail () throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        usersActivityService.delete(testUsersActivity);
        assertFalse(usersActivityService.delete(testUsersActivity));
    }

    @Test
    public void shouldReturnTrueIfDeleteSuccessful() throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        assertTrue(usersActivityService.delete(testUsersActivity));
    }

    @Test
    public void shouldReturnNullIfIdDoesNotExist() throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        usersActivityService.delete(testUsersActivity);
        assertNull(usersActivityService.getById(testUsersActivity.getId()));
    }

    @Test
    public void shouldReturnEmptyListIfUserDoesNotExist() throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        userService.delete(testUser);
        List<UsersActivity> usersActivities = usersActivityService.getByUser(testUser);
        assertEquals(0, usersActivities.size());
    }

    @Test
    public void shouldReturnEmptyListIfActivityNotExist() throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        activityService.delete(testActivity);
        List<UsersActivity> usersActivities = usersActivityService.getByUser(testUser);
        assertEquals(0, usersActivities.size());
        usersActivityService.delete(testUsersActivity);
    }

    @Test
    public void shouldReturnEmptyPortionListIfUserDoesNotExist() throws ServiceException {
        UsersActivityService usersActivityService = new UsersActivityService();
        UserService userService = new UserService();
        ActivityService activityService = new ActivityService();
        User testUser = userService.getByLogin("testUser");
        Activity testActivity = activityService.getByName("TestActivity");
        UsersActivity testUsersActivity = usersActivityService.add(new UsersActivity(0, testUser, testActivity, 1, UsersActivityStatus.BOOKED));
        userService.delete(testUser);
        List<UsersActivity> usersActivities = usersActivityService.getPortionByUser(testUser, "id", 0,1, "desc");
        assertEquals(0, usersActivities.size());
        usersActivityService.delete(testUsersActivity);
    }

}

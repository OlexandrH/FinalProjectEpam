package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.service.exception.ServiceException;

public class TestUserService {

    @Before
    public void createTestEntities () throws ServiceException {
        UserService userService = new UserService();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        userService.add(testUser);
    }

    @After
    public void deleteTestEntities () throws ServiceException {
        UserService userService = new UserService();
        User testUser = userService.getByLogin("test");
        if(testUser != null) {
            userService.delete(testUser);
        }
    }

    @Test
    public void shouldReturnFalseIfUpdateUserFail () throws ServiceException {
        UserService userService = new UserService();
        User testUser = userService.getByLogin("test");
        userService.delete(testUser);
        testUser.setName("Test2");
        assertFalse(userService.update(testUser));
    }

    @Test
    public void shouldReturnTrueIfUpdateUserSuccessful () throws ServiceException {
        UserService userService = new UserService();
        User testUser = userService.getByLogin("test");
        testUser.setName("Test2");
        assertTrue(userService.update(testUser));
    }

    @Test
    public void shouldReturnFalseIfDeleteUserFail () throws ServiceException {
        UserService userService = new UserService();
        User testUser = userService.getByLogin("test");
        userService.delete(testUser);
        assertFalse(userService.delete(testUser));
    }

    @Test
    public void shouldReturnTrueIfDeleteUserSuccessful () throws ServiceException {
        UserService userService = new UserService();
        User testUser = userService.getByLogin("test");
        assertTrue(userService.delete(testUser));
    }

    @Test
    public void shouldReturnNullIfUserDoesNotExist() throws ServiceException {
        UserService userService = new UserService();
        User testUser = userService.getByLogin("test");
        userService.delete(testUser);
        assertNull(userService.getById(testUser.getId()));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionIfUserLoginExists () throws ServiceException {
        UserService userService = new UserService();
        User testUser = new User(0, "test", "test", UserRole.USER, "TestUser2");
        userService.add(testUser);
    }

}

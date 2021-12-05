package dao;

import org.junit.After;
import org.junit.Test;
import ua.my.oblikchasu.db.dao.UserDAO;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.db.exception.DBException;

import java.util.List;

import static org.junit.Assert.*;

public class TestUserDAO {

    @After
    public void deleteTestUser() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = userDAO.findByLogin("test");
        if (testUser != null) {
            userDAO.delete(testUser);
        }
    }

    @Test
    public void shouldAddUser() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        assertNull(userDAO.findByLogin("test"));
        userDAO.create(testUser);
        assertNotNull(userDAO.findByLogin("test"));
    }

    @Test
    public void shouldUpdateUser() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        testUser = userDAO.create(testUser);
        testUser.setName("Test2");
        userDAO.update(testUser);
        assertEquals("Test2", userDAO.findById(testUser.getId()).getName());
    }

    @Test
    public void shouldDeleteUser() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        testUser = userDAO.create(testUser);
        assertNotNull(userDAO.findByLogin("test"));
        userDAO.delete(testUser);
        assertNull(userDAO.findByLogin("test"));
    }

    @Test
    public void shouldFindCountUsers() throws DBException {
        UserDAO userDAO = new UserDAO();
        int userCount = userDAO.findCount();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        userDAO.create(testUser);
        int userCountAfterAddingUser = userDAO.findCount();
        assertEquals(1, userCountAfterAddingUser - userCount);
    }

    @Test
    public void shouldFindAllUsers() throws DBException {
        UserDAO userDAO = new UserDAO();
        int userCount = userDAO.findCount();
        List<User> allUsers = userDAO.findAll();
        assertEquals(userCount, allUsers.size());
    }

    @Test
    public void shouldFindByName() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        userDAO.create(testUser);
        assertEquals(testUser, userDAO.findByName("Test"));

    }

    @Test
    public void shouldFindByLogin() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        userDAO.create(testUser);
        assertEquals(testUser, userDAO.findByLogin("test"));
    }

    @Test
    public void shouldFindById() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUser = new User(0, "test", "test", UserRole.USER, "Test");
        testUser = userDAO.create(testUser);
        assertEquals(testUser, userDAO.findById(testUser.getId()));
    }

    @Test
    public void shouldFindSortedPortionByLogin() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUserA = new User(0, "000testA", "test", UserRole.USER, "000TestA");
        User testUserB = new User(0, "000testB", "test", UserRole.USER, "000TestB");
        User testUserC = new User(0, "000testC", "test", UserRole.USER, "000TestC");
        testUserA = userDAO.create(testUserA);
        testUserB = userDAO.create(testUserB);
        testUserC = userDAO.create(testUserC);
        List<User> users = userDAO.findSortedPortion("login", 0, 3, "asc");
        assertEquals(3, users.size());
        assertEquals(testUserA, users.get(0));
        assertEquals(testUserB, users.get(1));
        assertEquals(testUserC, users.get(2));
        userDAO.delete(testUserA);
        userDAO.delete(testUserB);
        userDAO.delete(testUserC);
    }

    @Test
    public void shouldFindSortedPortionByName() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUserA = new User(0, "000testA", "test", UserRole.USER, "000TestA");
        User testUserB = new User(0, "000testB", "test", UserRole.USER, "000TestB");
        User testUserC = new User(0, "000testC", "test", UserRole.USER, "000TestC");
        testUserA = userDAO.create(testUserA);
        testUserB = userDAO.create(testUserB);
        testUserC = userDAO.create(testUserC);
        List<User> users = userDAO.findSortedPortion("name", 0, 3, "asc");
        assertEquals(3, users.size());
        assertEquals(testUserA, users.get(0));
        assertEquals(testUserB, users.get(1));
        assertEquals(testUserC, users.get(2));
        userDAO.delete(testUserA);
        userDAO.delete(testUserB);
        userDAO.delete(testUserC);
    }

    @Test
    public void shouldFindSortedPortionById() throws DBException {
        UserDAO userDAO = new UserDAO();
        User testUserA = new User(0, "000testA", "test", UserRole.USER, "000TestA");
        User testUserB = new User(0, "000testB", "test", UserRole.USER, "000TestB");
        User testUserC = new User(0, "000testC", "test", UserRole.USER, "000TestC");
        testUserA = userDAO.create(testUserA);
        testUserB = userDAO.create(testUserB);
        testUserC = userDAO.create(testUserC);
        List<User> users = userDAO.findSortedPortion("id", 0, 3, "desc");
        assertEquals(3, users.size());
        assertEquals(testUserA, users.get(2));
        assertEquals(testUserB, users.get(1));
        assertEquals(testUserC, users.get(0));
        userDAO.delete(testUserA);
        userDAO.delete(testUserB);
        userDAO.delete(testUserC);
    }
}

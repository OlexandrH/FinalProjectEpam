package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.UserDAO;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.util.Encryptor;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    UserDAO userDAO = new UserDAO();
    User user = null;

    public User getById(int id) throws ServiceException {
        try {
            user = userDAO.findById(id);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        return user;
    }

    public User getByLogin(String login) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findByLogin(login);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        return user;
    }

    public User getByName(String name) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findByLogin(name);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        return user;
    }

    public List<User> getAll() throws ServiceException {
        List<User> allUsers = new LinkedList<>();
        try {
            allUsers = userDAO.findAll();
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        return allUsers;
    }

    public int getCount() throws ServiceException {
        int count = 0;
        try {
            count = userDAO.findCount();
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        return count;
    }


    public List<User> getSortedPortion(String sortedBy, int from, int amount, String order) throws ServiceException {
        List<User> users = new LinkedList<>();
        try {
            users = userDAO.findSortedPortion(sortedBy, from, amount, order);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        return users;
    }

    public List<User> getByRole(UserRole role) throws ServiceException {
        List<User> allUsers = new LinkedList<>();
        try {
            allUsers = userDAO.findAll();
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot find user", e);
        }
        allUsers = allUsers.stream()
                .filter((user) -> user.getRole() == role)
                .collect(Collectors.toList());
        return allUsers;
    }

    public boolean changePassword(User user, String newPassword) throws ServiceException {
        try {
            String encryptedPassword = Encryptor.encryptPassword(user.getLogin(), newPassword);
            user.setPassword(encryptedPassword);
            return userDAO.update(user);
        } catch (NoSuchAlgorithmException | DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot change user password", e);
        }
    }

    public boolean changeName(User user, String newName) throws ServiceException {
        user.setName(newName);
        try {
            return userDAO.update(user);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot change user name", e);
        }
    }

    public User add(User user) throws ServiceException {
        User createdUser = null;
        try {
            createdUser = userDAO.create(user);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot add user", e);
        }
        return createdUser;
    }

    public boolean update(User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot update user", e);
        }
    }


    public boolean delete(User user) throws ServiceException {
        try {
            return userDAO.delete(user);
        } catch (DBException e) {
            logger.error("Error", e);
            throw new ServiceException("Cannot delete user", e);
        }
    }
}

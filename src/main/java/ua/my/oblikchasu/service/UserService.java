package ua.my.oblikchasu.service;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.UserDAO;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.service.exception.ErrorMsg;
import ua.my.oblikchasu.service.exception.ServiceException;

import java.util.LinkedList;
import java.util.List;

public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    UserDAO userDAO = new UserDAO();

    public User getById(int id) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findById(id);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_FIND, e);
        }
        return user;
    }

    public User getByLogin(String login) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findByLogin(login);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_FIND, e);
        }
        return user;
    }

    public User getByName(String name) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findByLogin(name);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_FIND, e);
        }
        return user;
    }

    public List<User> getAll() throws ServiceException {
        List<User> allUsers = new LinkedList<>();
        try {
            allUsers = userDAO.findAll();
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_FIND, e);
        }
        return allUsers;
    }

    public int getCount() throws ServiceException {
        int count = 0;
        try {
            count = userDAO.findCount();
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_FIND, e);
        }
        return count;
    }


    public List<User> getSortedPortion(String sortedBy, int from, int amount, String order) throws ServiceException {
        List<User> users = new LinkedList<>();
        try {
            users = userDAO.findSortedPortion(sortedBy, from, amount, order);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.ACTIVITY_CANNOT_FIND, e);
        }
        return users;
    }

    public User add(User user) throws ServiceException {
        User createdUser = null;
        try {
            createdUser = userDAO.create(user);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_ADD, e);
        }
        return createdUser;
    }

    public boolean update(User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_UPDATE, e);
        }
    }

    public boolean delete(User user) throws ServiceException {
        try {
            return userDAO.delete(user);
        } catch (DBException e) {
            logger.error(ErrorMsg.ERROR, e);
            throw new ServiceException(ErrorMsg.USER_CANNOT_DELETE, e);
        }
    }
}

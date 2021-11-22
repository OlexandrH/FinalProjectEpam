package ua.my.oblikchasu.db.dao;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.util.LogMsg;
import ua.my.oblikchasu.db.ConnectionPool;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.db.exception.ErrorMsg;
import ua.my.oblikchasu.db.DBQuery;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements GenericDAO<User> {
    private static final Logger logger = Logger.getLogger(UserDAO.class);
    @Override
    public List<User> findAll() throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<User> users = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBQuery.SELECT_ALL_USERS);
            while (rs.next()) {
                User tempUser = new User (
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        UserRole.values()[rs.getInt("role_id")],
                        rs.getString("name"));
                users.add(tempUser);

            }
            return users;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }

    }

    public List<User> findSorted(String sortBy) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<User> users = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBQuery.SELECT_ALL_USERS + DBQuery.suffix.get(sortBy));
            while (rs.next()) {
                User tempUser = new User (
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        UserRole.values()[rs.getInt("role_id")],
                        rs.getString("name"));
                users.add(tempUser);

            }
            return users;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }

    }

    public List<User> findSortedPortion(String sortBy, int from, int amount) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<User> users = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_ALL_USERS + DBQuery.suffix.get(sortBy) + DBQuery.LIMIT);
            pstmt.setInt(1,from);
            pstmt.setInt(2,amount);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User tempUser = new User (
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        UserRole.values()[rs.getInt("role_id")],
                        rs.getString("name"));
                users.add(tempUser);

            }
            return users;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }

    }

    @Override
    public User findByName(String name) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_USER_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            User tempUser = null;
            if (rs.next()) {
                tempUser = new User (
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        UserRole.values()[rs.getInt("role_id")],
                        rs.getString("name"));
            }
            return tempUser;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public User findByLogin(String login) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            User tempUser = null;
            if (rs.next()) {
                tempUser = new User (
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        UserRole.values()[rs.getInt("role_id")],
                        rs.getString("name"));
            }
            return tempUser;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }


    @Override
    public User findById(int id) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        User user=null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_USER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            User tempUser = null;
            if (rs.next()) {
                tempUser = new User (
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        UserRole.values()[rs.getInt("role_id")],
                        rs.getString("name"));
            }
            return tempUser;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public User create(User user) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRole().ordinal());
            pstmt.setString(4, user.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException throwable) {
            logger.info(LogMsg.USER_ADD_FAIL + user.getLogin());
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
        logger.info(LogMsg.USER_ADDED + user.getLogin());
        return user;
    }

    @Override
    public boolean update(User user) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.UPDATE_USER);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setInt(3, user.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.USER_UPDATED + user.getLogin());
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.USER_UPDATE_FAIL + user.getId());
            logger.error(LogMsg.ERROR + user.getId());
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public boolean delete(User user) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.DELETE_USER);
            pstmt.setInt(1, user.getId());

            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.USER_DELETED + "id = " +user.getId());

                return true;
            } else {
                logger.error(LogMsg.USER_DELETE_FAIL + user.getLogin());
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.USER_DELETE_FAIL + user.getLogin());
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }
}

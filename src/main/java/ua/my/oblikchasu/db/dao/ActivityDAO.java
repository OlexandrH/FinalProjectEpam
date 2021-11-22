package ua.my.oblikchasu.db.dao;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.util.LogMsg;
import ua.my.oblikchasu.db.ConnectionPool;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.db.exception.ErrorMsg;
import ua.my.oblikchasu.db.DBQuery;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ActivityDAO implements GenericDAO<Activity>{
    private static final Logger logger = Logger.getLogger(ActivityDAO.class);

    @Override
    public List<Activity> findAll() throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<Activity> activities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBQuery.SELECT_ALL_ACTIVITIES);
            while (rs.next()) {
                activities.add(
                        new Activity(
                                rs.getInt("id"),
                                rs.getString("name"),
                                new ActivityCategory(rs.getInt("category_id"), null)
                        )
                );
            }
            return activities;
        } catch (SQLException throwable) {
            logger.error(ErrorMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    public List<Activity> findByCategory(int categoryId) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<Activity> activities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_ACTIVITIES_BY_CATEGORY);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                activities.add(new Activity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new ActivityCategory(rs.getInt("category_id"), null))
                );
            }
            return activities;
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
    public Activity findById(int id) throws DBException {
            Connection con = null;
            ResultSet rs = null;
            PreparedStatement pstmt = null;
            Activity activity = null;
            try {
                con = ConnectionPool.getConnection();
                pstmt = con.prepareStatement(DBQuery.SELECT_ACTIVITY_BY_ID);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    activity = new Activity(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                new ActivityCategory(rs.getInt("category_id"), null)
                            );
                }
                return activity;
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
    public Activity findByName(String name) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Activity activity = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_ACTIVITY_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                activity = new Activity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new ActivityCategory(rs.getInt("category_id"), null)
                );
            }
            return activity;
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
    public Activity create(Activity activity) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.INSERT_ACTIVITY, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, activity.getName());
            pstmt.setInt(2, activity.getCategory().getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                activity.setId(rs.getInt(1));
            }
        } catch (SQLException throwable) {
            logger.info(LogMsg.ACTIVITY_ADD_FAIL + activity.getName());
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
        logger.info(LogMsg.ACTIVITY_ADDED + activity.getName());
        return activity;
    }

    @Override
    public boolean update(Activity activity) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.UPDATE_ACTIVITY);
            pstmt.setString(1, activity.getName());
            pstmt.setInt(2, activity.getCategory().getId());
            pstmt.setInt(3, activity.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.ACTIVITY_UPDATED + activity.getId());
                return true;
            } else {
                logger.error(LogMsg.ACTIVITY_UPDATE_FAIL + activity.getId());
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.ACTIVITY_UPDATE_FAIL + activity.getId());
            logger.error(LogMsg.ERROR + throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public boolean delete(Activity activity) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.DELETE_ACTIVITY);
            pstmt.setInt(1, activity.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.ACTIVITY_DELETED + activity.getName());
                return true;
            } else {
                logger.error(LogMsg.ACTIVITY_DELETE_FAIL + activity.getName());
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.ACTIVITY_DELETE_FAIL + activity.getName());
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }
}
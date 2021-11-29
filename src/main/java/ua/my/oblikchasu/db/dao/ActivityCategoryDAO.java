package ua.my.oblikchasu.db.dao;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.util.LogMsg;
import ua.my.oblikchasu.db.DBService;
import ua.my.oblikchasu.db.entity.*;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.db.exception.ErrorMsg;
import ua.my.oblikchasu.db.DBQuery;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ActivityCategoryDAO implements GenericDAO<ActivityCategory>{
    private static final Logger logger = Logger.getLogger(ActivityCategoryDAO.class);
    @Override
    public List<ActivityCategory> findAll() throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<ActivityCategory> activityCategories = new LinkedList<>();
        try {
            con = DBService.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBQuery.SELECT_ALL_CATEGORIES);
            while (rs.next()) {
                activityCategories.add(
                        new ActivityCategory(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                );
            }
            return activityCategories;
        } catch (SQLException throwable) {
            logger.error(ErrorMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    public int findCount () throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        int recordNumber = 0;
        try {
            con = DBService.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM category");
            if (rs.next()) {
                recordNumber = rs.getInt(1);
            }
            return recordNumber;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    public List<ActivityCategory> findSortedPortion(String sortBy, int from, int amount, String order) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<ActivityCategory> activityCategories = new LinkedList<>();
        try {
            con = DBService.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_ALL_CATEGORIES +
                    DBQuery.ORDER_BY + sortBy + " " + order + DBQuery.LIMIT);
            pstmt.setInt(1, from);
            pstmt.setInt(2, amount);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                activityCategories.add(
                        new ActivityCategory(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                );
            }
            return activityCategories;
        } catch (SQLException throwable) {
            logger.error(ErrorMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public ActivityCategory findByName(String name) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ActivityCategory activityCategory = null;
        try {
            con = DBService.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_CATEGORY_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                activityCategory = new ActivityCategory(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
            return activityCategory;
        } catch (SQLException throwable) {
            logger.error(ErrorMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public ActivityCategory findById(int id) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ActivityCategory activityCategory = null;
        try {
            con = DBService.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_CATEGORY_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                activityCategory = new ActivityCategory(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
            return activityCategory;
        } catch (SQLException throwable) {
            logger.error(ErrorMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public ActivityCategory create(ActivityCategory activityCategory) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = DBService.getConnection();
            pstmt = con.prepareStatement(DBQuery.INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, activityCategory.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                activityCategory.setId(rs.getInt(1));
            }
        } catch (SQLException throwable) {
            logger.info(LogMsg.ACTIVITY_CATEGORY_ADD_FAIL + activityCategory);
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
        logger.info(LogMsg.ACTIVITY_CATEGORY_ADDED + activityCategory);
        return activityCategory;
    }

    @Override
    public boolean update(ActivityCategory activityCategory) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBService.getConnection();
            pstmt = con.prepareStatement(DBQuery.UPDATE_CATEGORY);
            pstmt.setString(1, activityCategory.getName());
            pstmt.setInt(2, activityCategory.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.ACTIVITY_CATEGORY_UPDATED + activityCategory);
                return true;
            } else {
                logger.error(LogMsg.ACTIVITY_CATEGORY_UPDATE_FAIL + activityCategory);
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.ACTIVITY_CATEGORY_UPDATE_FAIL + activityCategory);
            logger.error(LogMsg.ERROR + throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public boolean delete(ActivityCategory activityCategory) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBService.getConnection();
            pstmt = con.prepareStatement(DBQuery.DELETE_CATEGORY);
            pstmt.setInt(1, activityCategory.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.ACTIVITY_CATEGORY_DELETED + activityCategory);
                return true;
            } else {
                logger.error(LogMsg.ACTIVITY_CATEGORY_DELETE_FAIL + activityCategory);
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.ACTIVITY_CATEGORY_DELETE_FAIL + activityCategory);
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }
}

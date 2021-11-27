package ua.my.oblikchasu.db.dao;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.UsersActivityStatus;
import ua.my.oblikchasu.util.LogMsg;
import ua.my.oblikchasu.db.ConnectionPool;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UsersActivity;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.db.exception.ErrorMsg;
import ua.my.oblikchasu.db.DBQuery;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersActivityDAO implements GenericDAO<UsersActivity> {

    private static final Logger logger = Logger.getLogger(UsersActivityDAO.class);

    @Override
    public List<UsersActivity> findAll() throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBQuery.SELECT_ALL_USERS_ACTIVITIES);
            while (rs.next()) {
                usersActivities.add(constructUsersActivity(rs));
            }
            return usersActivities;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    public int findCountByUser(User user) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        int recordNumber = 0;
        try {
            con = ConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM users_activity WHERE user_id= " + user.getId());
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

    public int findCount() throws DBException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        int recordNumber = 0;
        try {
            con = ConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM users_activity");
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

    public int findCountUsersByActivity(Activity activity) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int recordNumber=0;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement("SELECT COUNT(DISTINCT user_id) from users_activity WHERE activity_id = ?");
            pstmt.setInt(1, activity.getId());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                recordNumber = rs.getInt(1);
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
        return recordNumber;
    }

    public int findTotalTimeByActivity(Activity activity) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int recordNumber=0;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement("SELECT SUM(amount_time) from users_activity WHERE activity_id = ?");
            pstmt.setInt(1, activity.getId());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                recordNumber = rs.getInt(1);
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
        return recordNumber;
    }

    public List<UsersActivity> findSortedPortionByUser (User user, String sortBy, int from, int amount, String order) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        String query = DBQuery.SELECT_USERS_ACTIVITY_BY_USER +
                DBQuery.ORDER_BY +
                sortBy + " " +
                order + DBQuery.LIMIT;

        if(sortBy.equals("activity")) {
            query = DBQuery.SELECT_USERS_ACTIVITY_SORTED_BY_ACTIVITY +
                    order +
                    DBQuery.LIMIT;
        }

        if(sortBy.equals("category")) {
            query = DBQuery.SELECT_USERS_ACTIVITY_SORTED_BY_CATEGORY +
                    order +
                    DBQuery.LIMIT;
        }

        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, from);
            pstmt.setInt(3, amount);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                usersActivities.add(constructUsersActivity(rs));
            }
            return usersActivities;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public List<UsersActivity> findSortedPortion (String sortBy, int from, int amount, String order) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_ALL_USERS_ACTIVITIES +
                    DBQuery.ORDER_BY +
                    sortBy + " " +
                    order + DBQuery.LIMIT);

            pstmt.setInt(1, from);
            pstmt.setInt(2, amount);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                usersActivities.add(constructUsersActivity(rs));
            }
            return usersActivities;
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
    public UsersActivity findByName(String name) {
        throw new UnsupportedOperationException(ErrorMsg.UNSUPPORTED_OPERATION);
    }

    @Override
    public UsersActivity findById(int id) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        UsersActivity usersActivity = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_USERS_ACTIVITY_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usersActivity = constructUsersActivity(rs);
            }
            return usersActivity;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public List<UsersActivity> findByUser(User user) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_USERS_ACTIVITY_BY_USER);
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usersActivities.add(constructUsersActivity(rs));
            }
            return usersActivities;
        } catch (SQLException throwable) {
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public List<UsersActivity> findByActivity(Activity activity) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<UsersActivity> usersActivities = new LinkedList<>();
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.SELECT_USERS_ACTIVITY_BY_ACTIVITY);
            pstmt.setInt(1, activity.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usersActivities.add(constructUsersActivity(rs));
            }
            return usersActivities;
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
    public UsersActivity create(UsersActivity usersActivity) throws DBException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.INSERT_USERS_ACTIVITY, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, usersActivity.getUser().getId());
            pstmt.setInt(2, usersActivity.getActivity().getId());
            pstmt.setLong(3, usersActivity.getAmountTime());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                usersActivity.setId(rs.getInt(1));
            }
        } catch (SQLException throwable) {
            logger.info(LogMsg.USERS_ACTIVITY_ADD_FAIL + usersActivity);

            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closerResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
        logger.info(LogMsg.USERS_ACTIVITY_ADDED + usersActivity);

        return usersActivity;
    }

    @Override
    public boolean update(UsersActivity usersActivity) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.UPDATE_USERS_ACTIVITY);
            pstmt.setInt(1, usersActivity.getStatus().ordinal());
            pstmt.setLong(2, usersActivity.getAmountTime());
            pstmt.setInt(3, usersActivity.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.USERS_ACTIVITY_UPDATED + usersActivity);

                return true;
            } else {
                logger.error(LogMsg.USERS_ACTIVITY_UPDATE_FAIL + usersActivity);
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.USERS_ACTIVITY_UPDATE_FAIL + usersActivity);
            logger.error(LogMsg.ERROR + throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    @Override
    public boolean delete(UsersActivity usersActivity) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionPool.getConnection();
            pstmt = con.prepareStatement(DBQuery.DELETE_USERS_ACTIVITY);
            pstmt.setInt(1, usersActivity.getId());
            if(pstmt.executeUpdate() == 1) {
                logger.info(LogMsg.USERS_ACTIVITY_DELETED + usersActivity);
                return true;
            } else {
                logger.error(LogMsg.USERS_ACTIVITY_DELETE_FAIL + usersActivity);
                return false;
            }
        } catch (SQLException throwable) {
            logger.error(LogMsg.USERS_ACTIVITY_DELETE_FAIL + usersActivity);
            logger.error(LogMsg.ERROR, throwable);
            throw new DBException(ErrorMsg.DB_CONN_ERROR, throwable);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    private UsersActivity constructUsersActivity (ResultSet rs) throws SQLException {

                return new UsersActivity(
                rs.getInt("id"),
                new User(rs.getInt("user_id")),
                new Activity(rs.getInt("activity_id")),
                rs.getLong("amount_time"),
                UsersActivityStatus.values() [rs.getInt("status")]
        );

    }
}

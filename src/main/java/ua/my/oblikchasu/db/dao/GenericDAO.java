package ua.my.oblikchasu.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.Entity;
import ua.my.oblikchasu.db.exception.DBException;
import ua.my.oblikchasu.db.exception.ErrorMsg;

public interface GenericDAO <T extends Entity>{

    Logger logger = Logger.getLogger(GenericDAO.class);
    List<T> findAll() throws DBException;
    T findByName (String name) throws  DBException;
    T findById(int id) throws DBException;
    T create (T entity) throws DBException;
    boolean update(T entity) throws DBException;
    boolean delete(T entity) throws DBException;

    default void closeStatement (Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwable) {
                logger.error(ErrorMsg.DB_STATEMENT_CLOSE_FAIL, throwable);
            }
        }
    }

    default void closeConnection(Connection con) {
        if (con != null) {
        try {
                con.close();
            } catch(SQLException throwable){
            logger.error(ErrorMsg.DB_CONN_CLOSE_FAIL, throwable);
            }
        }
    }

    default void closerResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch(SQLException throwable){
                logger.error(ErrorMsg.DB_RESULT_SET_CLOSE_FAIL, throwable);
            }
        }
    }
}

package ua.my.oblikchasu.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.dao.ActivityCategoryDAO;
import ua.my.oblikchasu.db.exception.ErrorMsg;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    public static final String DS_NAME = "jdbc/mydb";
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    //    private static DataSource ds;
//    static {
//        try {
//            Context initCtx = new InitialContext();
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
//            ds = (DataSource) envCtx.lookup(DS_NAME);
//        } catch (NamingException e) {
//            logger.fatal(ErrorMsg.DB_CONN_POOL_ERROR, throwable);
//        }
//    }

    ConnectionPool instance = null;
    //test datasource to work without server
    private static MysqlDataSource ds;
    static {
            ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8");
            ds.setUser("root");
            ds.setPassword("root");
    }
    private ConnectionPool () {
        //Empty constructor
    }

    public static Connection getConnection () throws SQLException {
        Connection con = ds.getConnection();
        return con;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }

    public synchronized ConnectionPool getInstance () {
        if(instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
}

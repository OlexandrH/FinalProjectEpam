package ua.my.oblikchasu.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBService {
    public static final String DS_NAME = "jdbc/mydb";
    private static final Logger logger = Logger.getLogger(DBService.class);

    DBService instance = null;
    //test datasource to work without server
    private static MysqlDataSource ds;

//        static {
//        try {
//            Context initCtx = new InitialContext();
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
//            ds = (MysqlDataSource) envCtx.lookup(DS_NAME);
//        } catch (NamingException e) {
//            logger.fatal(ErrorMsg.DB_CONN_POOL_ERROR, e);
//        }
//    }

    static {
            ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8");
            ds.setUser("root");
            ds.setPassword("root");
    }
    private DBService() {
        //Empty constructor
    }

    public static Connection getConnection () throws SQLException {
        Connection con = ds.getConnection();
        return con;
    }

}

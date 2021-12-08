package ua.my.oblikchasu.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DBService {

    private static DBService instance = null;

    private static MysqlDataSource ds;

    static {
            ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8");
            ds.setUser("root");
            ds.setPassword("root");
    }
    private DBService() {
        //Empty constructor
    }

    public static synchronized DBService getInstance () {
        if(instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    public Connection getConnection () throws SQLException {
        return ds.getConnection();
    }

}

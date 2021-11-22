package ua.my.oblikchasu.db;

import java.util.HashMap;
import java.util.Map;

public abstract class DBQuery {
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login= ?";
    public static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name= ?";
    public static final String INSERT_USER = "INSERT INTO user (login, pass, role_id, name) VALUES (?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE user SET pass = ?, name = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id=?";
    public static final String SELECT_ALL_USERS_SORTED = "SELECT * FROM user ORDER BY ";

    public static final String SELECT_ALL_USERS_ACTIVITIES = "SELECT * FROM users_activity";
    public static final String SELECT_USERS_ACTIVITIES_PAGE_BY_ACTIVITY ="select * from users_activity uact inner join activity act on uact.id = act.id order by act.name";
    public static final String SELECT_USERS_ACTIVITY_BY_USER = "SELECT * FROM users_activity WHERE user_id=?";
    public static final String SELECT_USERS_ACTIVITY_BY_ID = "SELECT * FROM users_activity WHERE id=?";
    public static final String SELECT_USERS_ACTIVITY_BY_ACTIVITY = "SELECT * FROM users_activity WHERE activity_id=?";
    public static final String INSERT_USERS_ACTIVITY = "INSERT INTO users_activity (user_id, activity_id, amount_time) VALUES (?,?,?)";
    public static final String UPDATE_USERS_ACTIVITY = "UPDATE users_activity SET status = ?, amount_time = ? WHERE id = ?";
    public static final String DELETE_USERS_ACTIVITY = "DELETE FROM users_activity WHERE id=?";


    public static final String SELECT_ALL_ACTIVITIES = "SELECT * FROM activity";
    public static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM activity WHERE id=?";
    public static final String SELECT_ACTIVITY_BY_NAME = "SELECT * FROM activity WHERE name=?";
    public static final String SELECT_ACTIVITIES_BY_CATEGORY = "SELECT * FROM activity WHERE category_id=?";
    public static final String INSERT_ACTIVITY = "INSERT INTO activity (name, category_id) VALUES (?,?)";
    public static final String UPDATE_ACTIVITY = "UPDATE activity SET name = ?, category_id = ? WHERE id = ?";
    public static final String DELETE_ACTIVITY = "DELETE FROM activity WHERE id=?";


    public static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
    public static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
    public static final String INSERT_CATEGORY = "INSERT INTO category (name) VALUES (?)";
    public static final String UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE id = ?";
    public static final String DELETE_CATEGORY = "DELETE FROM category WHERE id=?";
    public static final String SELECT_USERS_ACTIVITY_SORTED_PAGE = "SELECT * FROM users_activity ORDER BY ? ? LIMIT ?, ?";


    public static final String LIMIT = " LIMIT ?, ?";
    public static final String SIMPLE_LIMIT = " LIMIT ";
    public static final String ORDER_BY = " ORDER BY ";

    public static final Map<String, String> suffix;

    static {
        suffix = new HashMap<String, String>() {
            {
                put("name", " ORDER BY name");
                put("id", " ORDER BY id");
                put("login", " ORDER BY login");
                put("role", "ORDER BY role_id");
                put("category", " ORDER BY category_id");
                put("activity", " ORDER BY category_id");
                put("limit", " LIMIT");
                put("asc", " ASC");
                put("desc", " DESC");
            }
        };
    }

}

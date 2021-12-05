package ua.my.oblikchasu.db;

public abstract class DBQuery {
    private DBQuery () {}
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login= ?";
    public static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name= ?";
    public static final String INSERT_USER = "INSERT INTO user (login, pass, role_id, name) VALUES (?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE user SET pass = ?, name = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id=?";
    public static final String SELECT_ALL_USERS_SORTED = "SELECT * FROM user ORDER BY ";
    public static final String SELECT_USER_COUNT = "SELECT COUNT(*) FROM user";

    public static final String SELECT_ALL_USERS_ACTIVITIES = "SELECT * FROM users_activity";
    public static final String SELECT_USERS_ACTIVITIES_PAGE_BY_ACTIVITY ="select * from users_activity uact inner join activity act on uact.id = act.id order by act.name";
    public static final String SELECT_USERS_ACTIVITY_BY_USER = "SELECT * FROM users_activity WHERE user_id=?";
    public static final String SELECT_USERS_ACTIVITY_SORTED_BY_ACTIVITY = "select distinct users_activity.* from users_activity inner join activity on users_activity.activity_id = activity.id where user_id = ? order by activity.name ";
    public static final String SELECT_USERS_ACTIVITY_SORTED_BY_CATEGORY = "select users_activity.* from users_activity left join activity on users_activity.activity_id = activity.id left join category on activity.category_id = category.id where user_id = ? order by category.name ";
    public static final String SELECT_USERS_ACTIVITY_BY_ID = "SELECT * FROM users_activity WHERE id=?";
    public static final String SELECT_USERS_ACTIVITY_BY_ACTIVITY = "SELECT * FROM users_activity WHERE activity_id=?";
    public static final String INSERT_USERS_ACTIVITY = "INSERT INTO users_activity (user_id, activity_id, amount_time) VALUES (?,?,?)";
    public static final String UPDATE_USERS_ACTIVITY = "UPDATE users_activity SET status = ?, amount_time = ? WHERE id = ?";
    public static final String DELETE_USERS_ACTIVITY = "DELETE FROM users_activity WHERE id=?";
    public static final String SELECT_USERS_ACTIVITY_COUNT_BY_USER = "SELECT COUNT(*) FROM users_activity WHERE user_id= ";
    public static final String SELECT_USERS_ACTIVITY_COUNT = "SELECT COUNT(*) FROM users_activity";
    public static final String SELECT_USERS_COUNT_BY_ACTIVITY = "SELECT COUNT(DISTINCT user_id) from users_activity WHERE activity_id = ?";
    public static final String SELECT_TOTAL_TIME_BY_ACTIVITY = "SELECT SUM(amount_time) from users_activity WHERE activity_id = ?";

    public static final String SELECT_ALL_ACTIVITIES = "SELECT * FROM activity";
    public static final String SELECT_ACTIVITIES_SORTED_BY_CATEGORY = "SELECT * FROM activity INNER JOIN category ON category.id = activity.category_id ORDER BY category.name ";
    public static final String SELECT_ACTIVITIES_SORTED_BY_TOTAL_TIME = "select activity.id, activity.name, activity_id, category_id, sum(users_activity.amount_time) amount_time from \n" +
            "activity left join users_activity on users_activity.activity_id = activity.id \n" +
            " group by users_activity.activity_id order by amount_time ";
    public static final String SELECT_ACTIVITIES_SORTED_BY_USER_COUNT = "select activity.id, activity.name, activity_id, category_id, count(distinct user_id) userCount from \n" +
            "activity left join users_activity on users_activity.activity_id = activity.id \n" +
            " group by users_activity.activity_id order by userCount ";
    public static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM activity WHERE id=?";
    public static final String SELECT_ACTIVITY_BY_NAME = "SELECT * FROM activity WHERE name=?";
    public static final String SELECT_ACTIVITIES_BY_CATEGORY = "SELECT * FROM activity WHERE category_id=?";
    public static final String INSERT_ACTIVITY = "INSERT INTO activity (name, category_id) VALUES (?,?)";
    public static final String UPDATE_ACTIVITY = "UPDATE activity SET name = ?, category_id = ? WHERE id = ?";
    public static final String DELETE_ACTIVITY = "DELETE FROM activity WHERE id=?";
    public static final String SELECT_ACTIVITY_COUNT = "SELECT COUNT(*) FROM activity";

    public static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
    public static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
    public static final String INSERT_CATEGORY = "INSERT INTO category (name) VALUES (?)";
    public static final String UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE id = ?";
    public static final String DELETE_CATEGORY = "DELETE FROM category WHERE id=?";
    public static final String SELECT_CATEGORY_COUNT = "SELECT COUNT(*) FROM category";

    public static final String LIMIT = " LIMIT ?, ?";
    public static final String SIMPLE_LIMIT = " LIMIT ";
    public static final String ORDER_BY = " ORDER BY ";

    public static final String TABLE_USER = "user";
    public static final String TABLE_ACTIVITY = "activity";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_USERS_ACTIVITY = "users_activity";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_CATEGORY_ID = "category_id";
}

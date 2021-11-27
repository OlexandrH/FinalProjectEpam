package ua.my.oblikchasu.db.entity;

public class UsersActivity extends Entity {
    private int id;
    private long amountTime;
    private UsersActivityStatus status;
    private User user;
    private Activity activity;

    public UsersActivity(int id, User user, Activity activity, long amountTime, UsersActivityStatus status) {
        this.id = id;
        this.user = user;
        this.activity = activity;
        this.amountTime = amountTime;
        this.status = status;
    }
    public UsersActivity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public long getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(long amountTime) {
        this.amountTime = amountTime;
    }

    public UsersActivityStatus getStatus() {
        return status;
    }

    public void setStatus(UsersActivityStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "id = " + id +
                " user = " + user.getId() + " (" + user.getLogin() + ")" +
                " activity = " + activity .getId() + " (" + activity.getName() + ")" +
                " amountTime = " + amountTime + " min" +
                " status = " + status;
    }

}

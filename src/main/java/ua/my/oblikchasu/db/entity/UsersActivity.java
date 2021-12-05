package ua.my.oblikchasu.db.entity;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersActivity that = (UsersActivity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

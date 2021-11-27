package ua.my.oblikchasu.db.entity;

public class Activity extends Entity{
    private int id;
    private String name;
    private int categoryId;
    private ActivityCategory category;
    private int userCount=0;
    private int totalTime=0;

    public Activity(int id, String name, ActivityCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Activity (int id) {
        this.id = id;
        this.name ="";
        this.category=new ActivityCategory(0,"");
    }

    public Activity () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ActivityCategory getCategory() {
        return category;
    }

    public void setCategory(ActivityCategory category) {
        this.category = category;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return
                "name = " + name +
                " id = " + id +
                " category = " + category;
    }
}

package ua.my.oblikchasu.db.entity;

public class Activity extends Entity{
    private int id;
    private String name;
    private int categoryId;
    private ActivityCategory category;

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

    @Override
    public String toString() {
        return "\r\nActivity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", categoryId='" + categoryId + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

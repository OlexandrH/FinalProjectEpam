package ua.my.oblikchasu.db.entity;

public class ActivityCategory extends Entity {
    private int id;
    private String name;

    public ActivityCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ActivityCategory(int id) {
        this.id = id;
        this.name="";
    }

    public ActivityCategory() {

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

    @Override
    public String toString() {
        return "name = " + name +
                " id = " + id;
    }
}

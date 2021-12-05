package ua.my.oblikchasu.db.entity;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCategory that = (ActivityCategory) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "name = " + name +
                " id = " + id;
    }
}

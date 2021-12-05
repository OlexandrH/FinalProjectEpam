package ua.my.oblikchasu.db.entity;

import java.util.Objects;

public class User extends Entity {
    private int id;
    private String login;
    private String name;
    private UserRole role;
    private String password;

    public User(int id, String login, String password, UserRole role, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public User (int id) {
        this.id = id;
        this.login = "";
        this.name = "";
        this.role = UserRole.USER;
        this.password = "";
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return
                " id = " + id +
                " login = " + login +
                " name = " + name +
                " role = " + role;
    }
}

package ua.my.oblikchasu.db.entity;

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
    public String toString() {
        return
                " id = " + id +
                " login = " + login +
                " name = " + name +
                " role = " + role;
    }
}

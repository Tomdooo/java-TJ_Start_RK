package cz.uhk.tj_start_rk.model.security;

public class SimpleIdPassword {
    private int id;
    private String password;

    public SimpleIdPassword(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package bg.softuni.filtersandauthentication.service.models;

import java.util.Set;

public class UserServiceModel {
    private String username;
    private String password;
    private Set<RoleServiceModel> authorities;

    public UserServiceModel() {
    }

    public UserServiceModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package entities;

import utilities.AccountUtilities;

import static utilities.AccountUtilities.validateUsername;

public abstract class User {
    private static int NEXT_ID = 1;

    private final int userId;
    private String username;
    private String password;

    public User(String username, String password) {
        this.userId = NEXT_ID++;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

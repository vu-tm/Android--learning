package com.example.khtt.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final int userId;
    private String displayName;
    private String password;

    public LoggedInUser(int userId, String displayName, String password) {
        this.userId = userId;
        this.displayName = displayName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
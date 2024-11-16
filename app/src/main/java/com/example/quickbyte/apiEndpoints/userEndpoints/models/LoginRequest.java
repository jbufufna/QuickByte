package com.example.quickbyte.apiEndpoints.userEndpoints.models;

public class LoginRequest {
    private String username;
    private String passwordHash;  // Ensure this matches your API's expectation

    public LoginRequest(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash +  // Protect sensitive info
                '}';
    }
}
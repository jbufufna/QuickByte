package com.example.quickbyte.API.DTO;

public class LoginRequestDTO {
    private String username;
    private String passwordHash;

    // Default constructor
    public LoginRequestDTO() {}

    // Constructor with all fields
    public LoginRequestDTO(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters and setters
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
}
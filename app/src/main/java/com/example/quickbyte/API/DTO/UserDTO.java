package com.example.quickbyte.API.DTO;

import java.time.LocalDateTime;

public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    //private LocalDateTime createdAt;
    private Boolean isActive;

    // Default constructor
    public UserDTO() {}

    // Constructor with all fields
    public UserDTO(Integer userId, String username, String email, String firstName, String lastName,
                   String phoneNumber, LocalDateTime createdAt, Boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        // this.createdAt = createdAt;
        this.isActive = isActive;
    }

    // Getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

   // public LocalDateTime getCreatedAt() {
   //     return createdAt;
   // }

   // public void setCreatedAt(LocalDateTime createdAt) {
    //    this.createdAt = createdAt;
   /// }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
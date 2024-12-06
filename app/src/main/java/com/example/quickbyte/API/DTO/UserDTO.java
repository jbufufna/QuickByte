package com.example.quickbyte.API.DTO;

import java.time.LocalDateTime;

public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean isActive;
    private int LoyaltyPoints;
    private String CardNumber;
    private int ExpiryMonth;
    private int ExpiryYear;
    private  int cvv;
    private Boolean IsDefaultCard;

    // Default constructor
    public UserDTO() {}

    // Constructor with all fields
    public UserDTO(Integer userId, String username, String email, String firstName, String lastName,
                   String phoneNumber, LocalDateTime createdAt, Boolean isActive, int LoyaltyPoints, String CardNumber, int ExpiryMonth, int ExpiryYear, Boolean IsDefaultCard, int cvv) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        // this.createdAt = createdAt;
        this.isActive = isActive;
        this.LoyaltyPoints = LoyaltyPoints;
        this.CardNumber = CardNumber;
        this.ExpiryMonth = ExpiryMonth;
        this.ExpiryYear = ExpiryYear;
        this.cvv = cvv;
        this.IsDefaultCard = IsDefaultCard;
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

    public int getLoyaltyPoints() {
        return LoyaltyPoints;
    }

    public void setLoyaltyPoints(int LoyaltyPoints) {
        this.LoyaltyPoints = LoyaltyPoints;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public int getExpiryMonth() {
        return ExpiryMonth;
    }

    public void setExpiryMonth(int ExpiryMonth) {
        this.ExpiryMonth = ExpiryMonth;
    }

    public int getExpiryYear() {
        return ExpiryYear;
    }

    public void setExpiryYear(int ExpiryYear) {
        this.ExpiryYear = ExpiryYear;
    }

    public Boolean getIsDefaultCard() {
        return IsDefaultCard;
    }

    public void setIsDefaultCard(Boolean IsDefaultCard) {
        this.IsDefaultCard = IsDefaultCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
package com.example.quickbyte.API.DTO;

import java.time.LocalDateTime;

public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean isActive;
    private int loyaltyPoints;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private  int cvv;
    private Boolean isDefaultCard;

    // Default constructor
    public UserDTO() {}

    // Constructor with all fields
    public UserDTO(Integer userId, String username, String email, String firstName, String lastName,
                   String phoneNumber, Boolean isActive, int loyaltyPoints, String cardNumber, int expiryMonth, int expiryYear, Boolean isDefaultCard, int cvv, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        // this.createdAt = createdAt;
        this.isActive = isActive;
        this.loyaltyPoints = loyaltyPoints;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.isDefaultCard = isDefaultCard;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int LoyaltyPoints) {
        this.loyaltyPoints = LoyaltyPoints;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.cardNumber = CardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int ExpiryMonth) {
        this.expiryMonth = ExpiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int ExpiryYear) {
        this.expiryYear = ExpiryYear;
    }

    public Boolean getIsDefaultCard() {
        return isDefaultCard;
    }

    public void setIsDefaultCard(Boolean IsDefaultCard) {
        this.isDefaultCard = IsDefaultCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
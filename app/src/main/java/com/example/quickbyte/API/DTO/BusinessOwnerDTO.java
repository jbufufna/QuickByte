package com.example.quickbyte.API.DTO;
import java.sql.Timestamp;

public class BusinessOwnerDTO {
    private Integer ownerId;
    private String username;
    private String email;
    private String passwordHash;
    private String contactNumber;
    private Timestamp createdAt;

    public BusinessOwnerDTO() {}

    public BusinessOwnerDTO(Integer ownerId, String username, String email, String passwordHash,
                            String contactNumber, Timestamp createdAt) {
        this.ownerId = ownerId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BusinessOwnerDTO{" +
                "ownerId=" + ownerId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

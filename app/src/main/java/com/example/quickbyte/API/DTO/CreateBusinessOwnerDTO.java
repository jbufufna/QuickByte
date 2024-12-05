package com.example.quickbyte.API.DTO;
public class CreateBusinessOwnerDTO {
    private String username;
    private String email;
    private String passwordHash;
    private String contactNumber;

    // Constructors
    public CreateBusinessOwnerDTO() {}

    public CreateBusinessOwnerDTO(String username, String email, String passwordHash,
                                  String contactNumber) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.contactNumber = contactNumber;
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

    @Override
    public String toString() {
        return "CreateBusinessOwnerDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}

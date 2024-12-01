package com.example.quickbyte.API.DTO;

public class BusinessInfoDTO {

    private String businessName;
    private String logoUrl;
    private String slogan;
    private String primaryColor;
    private String secondaryColor;

    public BusinessInfoDTO() {}

    public BusinessInfoDTO(String businessName, String logoUrl, String slogan, String primaryColor, String secondaryColor) {
        this.businessName = businessName;
        this.logoUrl = logoUrl;
        this.slogan = slogan;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    // Getters and Setters
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getSlogan() { return slogan; }
    public void setSlogan(String slogan) { this.slogan = slogan; }

    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }

    public String getSecondaryColor() { return secondaryColor; }
    public void setSecondaryColor(String secondaryColor) { this.secondaryColor = secondaryColor; }
}

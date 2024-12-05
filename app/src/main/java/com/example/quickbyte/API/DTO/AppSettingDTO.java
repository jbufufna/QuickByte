package com.example.quickbyte.API.DTO;

public class AppSettingDTO {

    private Integer settingId;
    private Integer ownerId;
    private String businessName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private String slogan;

    public AppSettingDTO() {}

    public AppSettingDTO(Integer settingId, Integer ownerId, String businessName, String logoUrl,
                         String primaryColor, String secondaryColor, String slogan) {
        this.settingId = settingId;
        this.ownerId = ownerId;
        this.businessName = businessName;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.slogan = slogan;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return "AppSettingDTO{" +
                "settingId=" + settingId +
                ", ownerId=" + ownerId +
                ", businessName='" + businessName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", primaryColor='" + primaryColor + '\'' +
                ", secondaryColor='" + secondaryColor + '\'' +
                ", slogan='" + slogan + '\'' +
                '}';
    }
}
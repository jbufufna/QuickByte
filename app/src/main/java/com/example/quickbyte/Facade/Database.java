package com.example.quickbyte.Facade;

public class Database {

    private String businessName;
    private String businessSlogan;
    private String businessLogo;
    private String businessPrimColor;
    private String businessSecColor;

    /*Get from database methods: */
    public String getBusinessName() {
        //businessName = "Example Business Name";
        return businessName;
    }

    public String getBusinessSlogan() {
        return businessSlogan;
    }

    public String getBusinessLogo() {
        return businessLogo;
    }

    public String getBusinessPrimColor() {
        return businessPrimColor;
    }

    public String getBusinessSecColor() {
        return businessSecColor;
    }

    /* Put in database methods*/
    public void putBusinessName(String inputBusinessName) {
        this.businessName = inputBusinessName;
    }

    public void putBusinessSlogan(String slogan) {
        this.businessSlogan = slogan;
    }

    public void putBusinessLogo(String logo) {
        this.businessLogo = logo;
    }

    public void putBusinessPrimColor(String color) {
        this.businessPrimColor = color;
    }

    public void putBusinessSecColor(String color) {
        this.businessSecColor = color;
    }


}

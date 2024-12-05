package com.example.quickbyte.API.DTO;

import java.math.BigDecimal;
public class CreateMenuItemDTO {
    private Integer categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public CreateMenuItemDTO() {}

    public CreateMenuItemDTO(Integer categoryId, String name, String description, BigDecimal price, String imageUrl) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
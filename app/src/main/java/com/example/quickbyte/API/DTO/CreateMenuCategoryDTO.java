package com.example.quickbyte.API.DTO;
public class CreateMenuCategoryDTO {
    private String name;
    private String description;

    public CreateMenuCategoryDTO() {}

    public CreateMenuCategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and setters
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
}

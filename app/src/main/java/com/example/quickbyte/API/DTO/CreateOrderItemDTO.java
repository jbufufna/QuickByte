package com.example.quickbyte.API.DTO;

public class CreateOrderItemDTO {
    private Integer menuItemId;
    private Integer quantity;

    public CreateOrderItemDTO() {
    }

    public CreateOrderItemDTO(Integer menuItemId, Integer quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CreateOrderItemDTO{" +
                "menuItemId=" + menuItemId +
                ", quantity=" + quantity +
                '}';
    }

}

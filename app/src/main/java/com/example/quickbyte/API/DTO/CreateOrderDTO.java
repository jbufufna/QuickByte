package com.example.quickbyte.API.DTO;

import java.util.List;

public class CreateOrderDTO {
    private Integer userId;
    private List<CreateOrderItemDTO> orderItems;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(Integer userId, List<CreateOrderItemDTO> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CreateOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CreateOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "CreateOrderDTO{" +
                "userId=" + userId +
                ", orderItems=" + orderItems +
                '}';
    }

}

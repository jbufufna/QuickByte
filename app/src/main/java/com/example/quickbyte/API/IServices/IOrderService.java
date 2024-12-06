package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.OrderDTO;
import com.example.quickbyte.API.DTO.CreateOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IOrderService {

    @GET("api/orders")
    Call<List<OrderDTO>> getAllOrders();

    @GET("api/orders/{orderId}")
    Call<OrderDTO> getOrderById(@Path("orderId") int orderId);

    @POST("api/orders")
    Call<OrderDTO> createOrderWithItems(@Body CreateOrderDTO orderDTO);

    @PUT("api/orders/{orderId}")
    Call<OrderDTO> updateOrder(@Path("orderId") int orderId, @Body OrderDTO updateOrder);

    @DELETE("api/orders/{orderId}")
    Call<Void> deleteOrder(@Path("orderId") int orderId);

    @PATCH("api/orders/{orderId}/status")
    Call<Void> updateOrderStatus(@Path("orderId") int orderId, @Query("status") String status);
}
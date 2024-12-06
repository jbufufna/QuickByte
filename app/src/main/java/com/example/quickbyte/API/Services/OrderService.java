package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.OrderDTO;
import com.example.quickbyte.API.DTO.CreateOrderDTO;
import com.example.quickbyte.API.IServices.IOrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderService {
    private static OrderService instance;
    private final IOrderService _orderService;

    private OrderService() {
        _orderService = ApiClient.getRetrofitInstance().create(IOrderService.class);
    }

    public static synchronized OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void getAllOrders(final ApiCallback<List<OrderDTO>> callback) {
        Call<List<OrderDTO>> call = _orderService.getAllOrders();
        call.enqueue(new Callback<List<OrderDTO>>() {
            @Override
            public void onResponse(Call<List<OrderDTO>> call, Response<List<OrderDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get orders: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<OrderDTO>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getOrderById(int orderId, final ApiCallback<OrderDTO> callback) {
        Call<OrderDTO> call = _orderService.getOrderById(orderId);
        call.enqueue(new Callback<OrderDTO>() {
            @Override
            public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get order: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<OrderDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void createOrderWithItems(CreateOrderDTO orderDTO, final ApiCallback<OrderDTO> callback) {
        Call<OrderDTO> call = _orderService.createOrderWithItems(orderDTO);
        call.enqueue(new Callback<OrderDTO>() {
            @Override
            public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create order: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<OrderDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateOrder(int orderId, OrderDTO updateOrder, final ApiCallback<OrderDTO> callback) {
        Call<OrderDTO> call = _orderService.updateOrder(orderId, updateOrder);
        call.enqueue(new Callback<OrderDTO>() {
            @Override
            public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to update order: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<OrderDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteOrder(int orderId, final ApiCallback<Void> callback) {
        Call<Void> call = _orderService.deleteOrder(orderId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to delete order: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateOrderStatus(int orderId, String status, final ApiCallback<Void> callback) {
        Call<Void> call = _orderService.updateOrderStatus(orderId, status);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to update order status: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}
package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.MenuCategory;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.API.IServices.ICustomerMenuService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuItemService {

    private static MenuItemService instance;
    private final ICustomerMenuService customerMenuService;

    private MenuItemService() {
        customerMenuService = ApiClient.getRetrofitInstance().create(ICustomerMenuService.class);
    }

    public static synchronized MenuItemService getInstance() {
        if (instance == null) {
            instance = new MenuItemService();
        }
        return instance;
    }

    public void getAllItems(final ApiCallback<List<MenuItem>> callback) {
        Call<List<MenuItem>> call = customerMenuService.getAllItems();
        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to load items: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getItemById(int itemId, final ApiCallback<MenuItem> callback) {
        Call<MenuItem> call = customerMenuService.getItemById(itemId);
        call.enqueue(new Callback<MenuItem>() {
            @Override
            public void onResponse(Call<MenuItem> call, Response<MenuItem> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to load item: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MenuItem> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}
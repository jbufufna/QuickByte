package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.CreateMenuItemDTO;
import com.example.quickbyte.API.DTO.MenuItemDTO;
import com.example.quickbyte.API.IServices.IManageMenuItemService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageMenuItemService {
    private static ManageMenuItemService instance;
    private final IManageMenuItemService _manageMenuItemService;

    private ManageMenuItemService() {
        _manageMenuItemService = ApiClient.getRetrofitInstance().create(IManageMenuItemService.class);
    }

    public static synchronized ManageMenuItemService getInstance() {
        if (instance == null) {
            instance = new ManageMenuItemService();
        }
        return instance;
    }

    public void createMenuItem(CreateMenuItemDTO createMenuItemDTO, final ApiCallback<MenuItemDTO> callback) {
        Call<MenuItemDTO> call = _manageMenuItemService.createMenuItem(createMenuItemDTO);
        call.enqueue(new Callback<MenuItemDTO>() {
            @Override
            public void onResponse(Call<MenuItemDTO> call, Response<MenuItemDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create menu item: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MenuItemDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateMenuItem(MenuItemDTO menuItemDTO, final ApiCallback<MenuItemDTO> callback) {
        Call<MenuItemDTO> call = _manageMenuItemService.updateMenuItem(menuItemDTO);
        call.enqueue(new Callback<MenuItemDTO>() {
            @Override
            public void onResponse(Call<MenuItemDTO> call, Response<MenuItemDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to update menu item: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MenuItemDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteMenuItem(int itemId, final ApiCallback<Void> callback) {
        Call<Void> call = _manageMenuItemService.deleteMenuItem(itemId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to delete menu item: " + response.message());
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
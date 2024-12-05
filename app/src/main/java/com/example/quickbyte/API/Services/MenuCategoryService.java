package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.MenuCategoryDTO;
import com.example.quickbyte.API.DTO.CreateMenuCategoryDTO;
import com.example.quickbyte.API.IServices.IMenuCategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuCategoryService {
    private static MenuCategoryService instance;
    private final IMenuCategoryService _menuCategoryService;

    private MenuCategoryService() {
        _menuCategoryService = ApiClient.getRetrofitInstance().create(IMenuCategoryService.class);
    }

    public static synchronized MenuCategoryService getInstance() {
        if (instance == null) {
            instance = new MenuCategoryService();
        }
        return instance;
    }

    public void getAllMenuCategories(final ApiCallback<List<MenuCategoryDTO>> callback) {
        Call<List<MenuCategoryDTO>> call = _menuCategoryService.getAllMenuCategories();
        call.enqueue(new Callback<List<MenuCategoryDTO>>() {
            @Override
            public void onResponse(Call<List<MenuCategoryDTO>> call, Response<List<MenuCategoryDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get menu categories: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MenuCategoryDTO>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getMenuCategoryById(int categoryId, final ApiCallback<MenuCategoryDTO> callback) {
        Call<MenuCategoryDTO> call = _menuCategoryService.getMenuCategoryById(categoryId);
        call.enqueue(new Callback<MenuCategoryDTO>() {
            @Override
            public void onResponse(Call<MenuCategoryDTO> call, Response<MenuCategoryDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get menu category: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MenuCategoryDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void createMenuCategory(CreateMenuCategoryDTO newCategory, final ApiCallback<MenuCategoryDTO> callback) {
        Call<MenuCategoryDTO> call = _menuCategoryService.createMenuCategory(newCategory);
        call.enqueue(new Callback<MenuCategoryDTO>() {
            @Override
            public void onResponse(Call<MenuCategoryDTO> call, Response<MenuCategoryDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create menu category: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MenuCategoryDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateMenuCategory(int categoryId, CreateMenuCategoryDTO updateCategory, final ApiCallback<MenuCategoryDTO> callback) {
        Call<MenuCategoryDTO> call = _menuCategoryService.updateMenuCategory(categoryId, updateCategory);
        call.enqueue(new Callback<MenuCategoryDTO>() {
            @Override
            public void onResponse(Call<MenuCategoryDTO> call, Response<MenuCategoryDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to update menu category: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MenuCategoryDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteMenuCategory(int categoryId, final ApiCallback<Void> callback) {
        Call<Void> call = _menuCategoryService.deleteMenuCategory(categoryId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to delete menu category: " + response.message());
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
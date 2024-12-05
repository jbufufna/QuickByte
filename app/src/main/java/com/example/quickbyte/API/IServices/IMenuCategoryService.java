package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.MenuCategoryDTO;
import com.example.quickbyte.API.DTO.CreateMenuCategoryDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IMenuCategoryService {

    @GET("api/menu-categories")
    Call<List<MenuCategoryDTO>> getAllMenuCategories();

    @GET("api/menu-categories/{categoryId}")
    Call<MenuCategoryDTO> getMenuCategoryById(@Path("categoryId") int categoryId);

    @POST("api/menu-categories")
    Call<MenuCategoryDTO> createMenuCategory(@Body CreateMenuCategoryDTO newCategory);

    @PUT("api/menu-categories/{categoryId}")
    Call<MenuCategoryDTO> updateMenuCategory(@Path("categoryId") int categoryId, @Body CreateMenuCategoryDTO updateCategory);

    @DELETE("api/menu-categories/{categoryId}")
    Call<Void> deleteMenuCategory(@Path("categoryId") int categoryId);
}
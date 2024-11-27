package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.MenuCategory;
import com.example.quickbyte.API.DTO.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICustomerMenuService {

    @GET("/api/customer/home/categories")
    Call<List<MenuCategory>> getMenuCategories();

    // Add this method for fetching items by category
    @GET("/api/customer/home/categories/{categoryId}/items")
    Call<List<MenuItem>> getItemsByCategory(@Path("categoryId") int categoryId);

    @GET("/api/customer/home/popular-items")
    Call<List<MenuItem>> getPopularItems();
}
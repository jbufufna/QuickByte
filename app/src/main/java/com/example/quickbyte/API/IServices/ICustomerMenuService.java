package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICustomerMenuService {

    @GET("/api/customer/home/categories")
    Call<List<MenuItem>> getMenuCategories();

    @GET("/api/customer/home/popular-items")
    Call<List<MenuItem>> getPopularItems();

    @GET("/api/customer/home/special-offers")
    Call<List<MenuItem>> getSpecialOffers();
}
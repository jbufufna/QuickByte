package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.CreateMenuItemDTO;
import com.example.quickbyte.API.DTO.MenuItemDTO;

import retrofit2.Call;
import retrofit2.http.*;

public interface IManageMenuItemService {

    @POST("api/menu-items")
    Call<MenuItemDTO> createMenuItem(@Body CreateMenuItemDTO createMenuItemDTO);

    @PUT("api/menu-items")
    Call<MenuItemDTO> updateMenuItem(@Body MenuItemDTO menuItemDTO);

    @DELETE("api/menu-items/{itemId}")
    Call<Void> deleteMenuItem(@Path("itemId") int itemId);

}
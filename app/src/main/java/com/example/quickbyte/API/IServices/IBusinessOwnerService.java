package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.BusinessOwnerDTO;
import com.example.quickbyte.API.DTO.CreateBusinessOwnerDTO;
import com.example.quickbyte.API.DTO.LoginRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IBusinessOwnerService {

    @GET("api/business-owners")
    Call<List<BusinessOwnerDTO>> getAllBusinessOwners();

    @PUT("api/business-owners/{ownerId}")
    Call<BusinessOwnerDTO> updateBusinessOwner(@Path("ownerId") int ownerId, @Body CreateBusinessOwnerDTO updateOwner);

    @DELETE("api/business-owners/{ownerId}")
    Call<Void> deleteBusinessOwner(@Path("ownerId") int ownerId);

    @GET("api/business-owners/{ownerId}")
    Call<BusinessOwnerDTO> getBusinessOwnerById(@Path("ownerId") int ownerId);

    @POST("api/business-owners")
    Call<BusinessOwnerDTO> createBusinessOwner(@Body CreateBusinessOwnerDTO newOwner);

    @POST("api/business-owners/login")
    Call<BusinessOwnerDTO> loginBusinessOwner(@Body LoginRequestDTO loginRequest);

}
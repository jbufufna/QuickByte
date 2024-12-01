package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IBusinessInfoService {

    @GET("/api/business-info/{id}")
    Call<BusinessInfoDTO> getBusinessInfo(@Path("id") int id);

    @POST("/api/business-info")
    Call<BusinessInfoDTO> saveBusinessInfo(@Body BusinessInfoDTO businessInfoDTO);

    @PUT("/api/business-info/{id}")
    Call<BusinessInfoDTO> updateBusinessInfo(@Path("id") int id, @Body BusinessInfoDTO businessInfoDTO);
}

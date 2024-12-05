package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.AppSettingDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IAppSettingService {

    @GET("api/app-settings")
    Call<List<AppSettingDTO>> getAllAppSettings();

    @GET("api/app-settings/{settingId}")
    Call<AppSettingDTO> getAppSettingById(@Path("settingId") int settingId);

    @POST("api/app-settings")
    Call<AppSettingDTO> createAppSetting(@Body AppSettingDTO appSettingDTO);

    @PUT("api/app-settings/{settingId}")
    Call<AppSettingDTO> updateAppSetting(@Path("settingId") int settingId, @Body AppSettingDTO appSettingDTO);

    @DELETE("api/app-settings/{settingId}")
    Call<Void> deleteAppSetting(@Path("settingId") int settingId);

    @GET("api/app-settings/owner/{ownerId}")
    Call<AppSettingDTO> getAppSettingsByOwnerId(@Path("ownerId") int ownerId);
}
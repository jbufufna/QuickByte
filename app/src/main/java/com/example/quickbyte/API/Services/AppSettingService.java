package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.AppSettingDTO;
import com.example.quickbyte.API.IServices.IAppSettingService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppSettingService {
    private static AppSettingService instance;
    private final IAppSettingService _appSettingService;

    private AppSettingService() {
        _appSettingService = ApiClient.getRetrofitInstance().create(IAppSettingService.class);
    }

    public static synchronized AppSettingService getInstance() {
        if (instance == null) {
            instance = new AppSettingService();
        }
        return instance;
    }

    public void getAllAppSettings(final ApiCallback<List<AppSettingDTO>> callback) {
        Call<List<AppSettingDTO>> call = _appSettingService.getAllAppSettings();
        call.enqueue(new Callback<List<AppSettingDTO>>() {
            @Override
            public void onResponse(Call<List<AppSettingDTO>> call, Response<List<AppSettingDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get app settings: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<AppSettingDTO>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getAppSettingById(int settingId, final ApiCallback<AppSettingDTO> callback) {
        Call<AppSettingDTO> call = _appSettingService.getAppSettingById(settingId);
        call.enqueue(new Callback<AppSettingDTO>() {
            @Override
            public void onResponse(Call<AppSettingDTO> call, Response<AppSettingDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get app setting: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AppSettingDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void createAppSetting(AppSettingDTO appSettingDTO, final ApiCallback<AppSettingDTO> callback) {
        Call<AppSettingDTO> call = _appSettingService.createAppSetting(appSettingDTO);
        call.enqueue(new Callback<AppSettingDTO>() {
            @Override
            public void onResponse(Call<AppSettingDTO> call, Response<AppSettingDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create app setting: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AppSettingDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateAppSetting(int settingId, AppSettingDTO appSettingDTO, final ApiCallback<AppSettingDTO> callback) {
        Call<AppSettingDTO> call = _appSettingService.updateAppSetting(settingId, appSettingDTO);
        call.enqueue(new Callback<AppSettingDTO>() {
            @Override
            public void onResponse(Call<AppSettingDTO> call, Response<AppSettingDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to update app setting: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AppSettingDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteAppSetting(int settingId, final ApiCallback<Void> callback) {
        Call<Void> call = _appSettingService.deleteAppSetting(settingId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to delete app setting: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getAppSettingsByOwnerId(int ownerId, final ApiCallback<AppSettingDTO> callback) {
        Call<AppSettingDTO> call = _appSettingService.getAppSettingsByOwnerId(ownerId);
        call.enqueue(new Callback<AppSettingDTO>() {
            @Override
            public void onResponse(Call<AppSettingDTO> call, Response<AppSettingDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get app settings by owner ID: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AppSettingDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}
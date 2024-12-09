package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.BusinessOwnerDTO;
import com.example.quickbyte.API.DTO.CreateBusinessOwnerDTO;
import com.example.quickbyte.API.DTO.LoginRequestDTO;
import com.example.quickbyte.API.IServices.IBusinessOwnerService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessOwnerService {
    private static BusinessOwnerService instance;
    private final IBusinessOwnerService _businessOwnerService;

    private BusinessOwnerService() {
        _businessOwnerService = ApiClient.getRetrofitInstance().create(IBusinessOwnerService.class);
    }

    public static synchronized BusinessOwnerService getInstance() {
        if (instance == null) {
            instance = new BusinessOwnerService();
        }
        return instance;
    }

    public void loginBusinessOwner(LoginRequestDTO loginRequest, final ApiCallback<BusinessOwnerDTO> callback) {
        Call<BusinessOwnerDTO> call = _businessOwnerService.loginBusinessOwner(loginRequest);
        call.enqueue(new Callback<BusinessOwnerDTO>() {
            @Override
            public void onResponse(Call<BusinessOwnerDTO> call, Response<BusinessOwnerDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BusinessOwnerDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}
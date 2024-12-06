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

    public void getAllBusinessOwners(final ApiCallback<List<BusinessOwnerDTO>> callback) {
        Call<List<BusinessOwnerDTO>> call = _businessOwnerService.getAllBusinessOwners();
        call.enqueue(new Callback<List<BusinessOwnerDTO>>() {
            @Override
            public void onResponse(Call<List<BusinessOwnerDTO>> call, Response<List<BusinessOwnerDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get business owners: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BusinessOwnerDTO>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateBusinessOwner(int ownerId, CreateBusinessOwnerDTO updateOwner, final ApiCallback<BusinessOwnerDTO> callback) {
        Call<BusinessOwnerDTO> call = _businessOwnerService.updateBusinessOwner(ownerId, updateOwner);
        call.enqueue(new Callback<BusinessOwnerDTO>() {
            @Override
            public void onResponse(Call<BusinessOwnerDTO> call, Response<BusinessOwnerDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to update business owner: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BusinessOwnerDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteBusinessOwner(int ownerId, final ApiCallback<Void> callback) {
        Call<Void> call = _businessOwnerService.deleteBusinessOwner(ownerId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to delete business owner: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }


    public void getBusinessOwnerById(int ownerId, final ApiCallback<BusinessOwnerDTO> callback) {
        Call<BusinessOwnerDTO> call = _businessOwnerService.getBusinessOwnerById(ownerId);
        call.enqueue(new Callback<BusinessOwnerDTO>() {
            @Override
            public void onResponse(Call<BusinessOwnerDTO> call, Response<BusinessOwnerDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get business owner: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BusinessOwnerDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void createBusinessOwner(CreateBusinessOwnerDTO newOwner, final ApiCallback<BusinessOwnerDTO> callback) {
        Call<BusinessOwnerDTO> call = _businessOwnerService.createBusinessOwner(newOwner);
        call.enqueue(new Callback<BusinessOwnerDTO>() {
            @Override
            public void onResponse(Call<BusinessOwnerDTO> call, Response<BusinessOwnerDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create business owner: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BusinessOwnerDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
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
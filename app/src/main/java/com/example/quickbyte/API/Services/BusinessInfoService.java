package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.IServices.IBusinessInfoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessInfoService {

    private static BusinessInfoService instance;
    private final IBusinessInfoService businessInfoService;

    private BusinessInfoService() {
        businessInfoService = ApiClient.getRetrofitInstance().create(IBusinessInfoService.class);
    }

    public static synchronized BusinessInfoService getInstance() {
        if (instance == null) {
            instance = new BusinessInfoService();
        }
        return instance;
    }

    public void getBusinessInfo(int id, final ApiCallback<BusinessInfoDTO> callback) {
        Call<BusinessInfoDTO> call = businessInfoService.getBusinessInfo(id);
        call.enqueue(new Callback<BusinessInfoDTO>() {
            @Override
            public void onResponse(Call<BusinessInfoDTO> call, Response<BusinessInfoDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to load business info: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BusinessInfoDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void saveOrUpdateBusinessInfo(BusinessInfoDTO businessInfoDTO, final ApiCallback<BusinessInfoDTO> callback) {
        // Make a single API call to the saveOrUpdate endpoint
        Call<BusinessInfoDTO> call = businessInfoService.saveOrUpdateBusinessInfo(businessInfoDTO);
        call.enqueue(new Callback<BusinessInfoDTO>() {
            @Override
            public void onResponse(Call<BusinessInfoDTO> call, Response<BusinessInfoDTO> response) {
                if (response.isSuccessful()) {
                    // Pass the response to the callback on success
                    callback.onSuccess(response.body());
                } else {
                    // Handle error cases with the response message
                    callback.onError("Failed to save or update business info: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BusinessInfoDTO> call, Throwable t) {
                // Handle network or other errors
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}

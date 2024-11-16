package com.example.quickbyte.apiEndpoints.userEndpoints;

import androidx.annotation.NonNull;

import com.example.quickbyte.apiEndpoints.ApiClient;
import com.example.quickbyte.apiEndpoints.userEndpoints.Interface.IUserEndpoint;
import com.example.quickbyte.apiEndpoints.userEndpoints.models.LoginRequest;
import com.example.quickbyte.apiEndpoints.userEndpoints.useDTO.UserCreationRequestDTO;
import com.example.quickbyte.apiEndpoints.userEndpoints.useDTO.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    private static UserService instance;
    private final IUserEndpoint api;

    private UserService() {
        this.api = ApiClient.getClient().create(IUserEndpoint.class);
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void loginUser(String username, String passwordHash, ApiCallback<UserDTO> callback) {
        LoginRequest loginRequest = new LoginRequest(username, passwordHash);
        Call<UserDTO> call = api.loginUser(loginRequest);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getAllUsers(ApiCallback<List<UserDTO>> callback) {
        Call<List<UserDTO>> call = api.getAllUsers();
        call.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get users: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void createUser(UserCreationRequestDTO userCreationRequest, ApiCallback<UserDTO> callback) {
        Call<UserDTO> call = api.createUser(userCreationRequest);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create user: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getUserById(int userId, ApiCallback<UserDTO> callback) {
        Call<UserDTO> call = api.getUserById(userId);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get user: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}

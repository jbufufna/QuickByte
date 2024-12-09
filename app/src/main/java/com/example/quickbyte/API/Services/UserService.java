package com.example.quickbyte.API.Services;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.LoginRequestDTO;
import com.example.quickbyte.API.DTO.UserCreationRequestDTO;
import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.IServices.IUserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    private static UserService instance;
    private final IUserService _userService;

    private UserService() {
        _userService = ApiClient.getRetrofitInstance().create(IUserService.class);
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void loginUser(String username, String password, final ApiCallback<UserDTO> callback) {
        LoginRequestDTO loginRequest = new LoginRequestDTO(username, password);
        Call<UserDTO> call = _userService.loginUser(loginRequest);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void createUser(UserCreationRequestDTO userCreationRequest, final ApiCallback<UserDTO> callback) {
        Call<UserDTO> call = _userService.createUser(userCreationRequest);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
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

    public void updateUser(int userId, UserDTO userDTO, final ApiCallback<UserDTO> callback) {
        Call<UserDTO> call = _userService.updateUser(userId, userDTO);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to update user: " + response.message());
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
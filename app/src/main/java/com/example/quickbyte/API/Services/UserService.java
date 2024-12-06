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

    public void getUserById(int userId, final ApiCallback<UserDTO> callback) {
        Call<UserDTO> call = _userService.getUserById(userId);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
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

    public void getAllUsers(final ApiCallback<List<UserDTO>> callback) {
        Call<List<UserDTO>> call = _userService.getAllUsers();
        call.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                if (response.isSuccessful()) {
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

    public void deleteUser(int userId, final ApiCallback<Void> callback) {
        Call<Void> call = _userService.deleteUser(userId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to delete user: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getUserByUsername(String username, final ApiCallback<UserDTO> callback) {
        Call<UserDTO> call = _userService.getUserByUsername(username);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
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

    public void isUsernameTaken(String username, final ApiCallback<Boolean> callback) {
        Call<Boolean> call = _userService.isUsernameTaken(username);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to check username: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void isEmailTaken(String email, final ApiCallback<Boolean> callback) {
        Call<Boolean> call = _userService.isEmailTaken(email);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to check email: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void searchUsers(String searchTerm, final ApiCallback<List<UserDTO>> callback) {
        Call<List<UserDTO>> call = _userService.searchUsers(searchTerm);
        call.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to search users: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }
}
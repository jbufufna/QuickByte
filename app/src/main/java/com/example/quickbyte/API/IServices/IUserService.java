package com.example.quickbyte.API.IServices;

import com.example.quickbyte.API.DTO.LoginRequestDTO;
import com.example.quickbyte.API.DTO.UserCreationRequestDTO;
import com.example.quickbyte.API.DTO.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IUserService {

    @POST("api/users/login")
    Call<UserDTO> loginUser(@Body LoginRequestDTO loginRequest);

    @GET("api/users")
    Call<List<UserDTO>> getAllUsers();

    @POST("api/users")
    Call<UserDTO> createUser(@Body UserCreationRequestDTO userCreationRequest);

    @GET("api/users/{id}")
    Call<UserDTO> getUserById(@Path("id") int userId);

    @PUT("api/users/{id}")
    Call<UserDTO> updateUser(@Path("id") int userId, @Body UserDTO userDTO);

    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Path("id") int userId);

    @GET("api/users/username/{username}")
    Call<UserDTO> getUserByUsername(@Path("username") String username);

    @GET("api/users/check-username/{username}")
    Call<Boolean> isUsernameTaken(@Path("username") String username);

    @GET("api/users/check-email/{email}")
    Call<Boolean> isEmailTaken(@Path("email") String email);

    @GET("api/users/search")
    Call<List<UserDTO>> searchUsers(@Query("searchTerm") String searchTerm);
}
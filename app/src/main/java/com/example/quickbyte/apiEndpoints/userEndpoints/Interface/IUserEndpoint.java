package com.example.quickbyte.apiEndpoints.userEndpoints.Interface;
import com.example.quickbyte.apiEndpoints.userEndpoints.models.LoginRequest;
import com.example.quickbyte.apiEndpoints.userEndpoints.useDTO.UserCreationRequestDTO;
import com.example.quickbyte.apiEndpoints.userEndpoints.useDTO.UserDTO;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserEndpoint {

    @POST("api/users/login")
    Call<UserDTO> loginUser(@Body LoginRequest loginRequest);

    @GET("api/users")
    Call<List<UserDTO>> getAllUsers();

    @POST("api/users")
    Call<UserDTO> createUser(@Body UserCreationRequestDTO userCreationRequest);

    @GET("api/users/{id}")
    Call<UserDTO> getUserById(@Path("id") int userId);

}

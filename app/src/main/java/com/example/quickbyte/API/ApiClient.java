package com.example.quickbyte.API;

import com.example.quickbyte.API.Serializers.LocalDateTimeDeserializer;
import com.example.quickbyte.API.IServices.ICustomerMenuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://quickbyte-repo-api-8993a41fc101.herokuapp.com/";
    private static Retrofit retrofit = null;

    private ApiClient() {
        // Private constructor to enforce Singleton pattern
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static <S> S getApiService(Class<S> serviceClass) {
        return getRetrofitInstance().create(serviceClass);
    }
}


//package com.example.quickbyte.API;
//
//import com.example.quickbyte.API.Serializers.LocalDateTimeDeserializer;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.time.LocalDateTime;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ApiClient {
//    private static final String BASE_URL = "https://quickbyte-repo-api-8993a41fc101.herokuapp.com/";
//    private static Retrofit retrofit = null;
//
//
//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//}
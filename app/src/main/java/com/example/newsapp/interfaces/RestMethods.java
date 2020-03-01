package com.example.newsapp.interfaces;

import com.example.newsapp.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Umair Adil on 10/12/2016.
 */

public interface RestMethods {

    //TODO Replace with your API's Login Method
    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);
}
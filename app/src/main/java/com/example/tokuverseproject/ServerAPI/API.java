package com.example.tokuverseproject.ServerAPI;

import com.example.tokuverseproject.Model.User;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("getUser.php")
    Call<List<User>> getUser();

    @POST("signUp.php")
    Call<User> signUpAction(@Body User user);
}

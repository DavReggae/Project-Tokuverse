package com.example.tokuverseproject.ServerAPI;

import com.example.tokuverseproject.Model.User;

import java.util.List;


import kotlin.jvm.JvmMultifileClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("getUser.php")
    Call<List<User>> getUser();

    @FormUrlEncoded
    @POST("signUp.php")
    Call<Void> signUpAction(
            @Field("username") String username,
            @Field("pass") String password,
            @Field("email") String email,
            @Field("phone_number") String phoneNumber
    );
}

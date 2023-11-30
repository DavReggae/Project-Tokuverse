package com.example.tokuverseproject.ServerAPI;

import com.example.tokuverseproject.Model.Hero;
import com.example.tokuverseproject.Model.HeroDetails;
import com.example.tokuverseproject.Model.NewFeeds;
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

    @GET("getHero.php")
    Call<List<Hero>> getHero();

    @GET("getNewFeeds.php")
    Call<List<NewFeeds>> getNewFeeds();

    @FormUrlEncoded
    @POST("login.php")
    Call<List<User>> logIn(@Field("username") String username,
                           @Field("pass") String password);

    @FormUrlEncoded
    @POST("getUser_ByID.php")
    Call<List<User>> getUser_ByID(@Field("id") String id);

    @FormUrlEncoded
    @POST("getHero_ByID.php")
    Call<List<Hero>> getHero_ByID(@Field("id") String id);

    @FormUrlEncoded
    @POST("getHeroDetails_ByUserID.php")
    Call<List<HeroDetails>> getHeroDetails_ByID(@Field("user_id") String id);

    @FormUrlEncoded
    @POST("getHeroDetails_ByUserID.php")
    Call<List<HeroDetails>> getHero_ByUserID(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("signUp.php")
    Call<Void> signUpAction(
            @Field("username") String username,
            @Field("pass") String password,
            @Field("email") String email,
            @Field("phone_number") String phoneNumber
    );

    @FormUrlEncoded
    @POST("createPost.php")
    Call<Void> createPostAction(
            @Field("user_id") String user_id,
            @Field("content") String content,
            @Field("date_post") String date_post
    );
    @FormUrlEncoded
    @POST("selectHero.php")
    Call<Void> selecHero(
            @Field("user_id") String user_id,
            @Field("hero_id") String hero_id);

    @FormUrlEncoded
    @POST("addHeroToUser.php")
    Call<Void> addHeroToUser(
            @Field("id") String id,
            @Field("hero_details_id") String hero_details_id);
}

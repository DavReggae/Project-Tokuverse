package com.example.tokuverseproject.Model;

import com.google.gson.annotations.SerializedName;

import okhttp3.Response;

public class User {
    @SerializedName("id")
    private  String id;
    @SerializedName("username")
    private  String username;

    @SerializedName("pass")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("profile_pic")
    private String profile_pic;

    @SerializedName("hero_id")
    private String hero_id;
    @SerializedName("coins")
    private String coins;

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }


    public User(String username, String password, String email, String phone_number) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }
}

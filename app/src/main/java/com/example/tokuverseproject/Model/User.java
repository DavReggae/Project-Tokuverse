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

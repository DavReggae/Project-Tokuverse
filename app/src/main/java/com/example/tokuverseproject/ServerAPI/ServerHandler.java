package com.example.tokuverseproject.ServerAPI;

import android.util.Log;

import com.example.tokuverseproject.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerHandler {
    public void getUser()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tokutech490.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        API Api = retrofit.create(API.class);
        Call<List<User>> call = Api.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                for(int i = 0; i < userList.size(); i++)
                {
                    Log.d("sucess", userList.get(i).getPassword());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }
}

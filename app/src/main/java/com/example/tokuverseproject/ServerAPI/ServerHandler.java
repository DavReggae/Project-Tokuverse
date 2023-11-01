package com.example.tokuverseproject.ServerAPI;

import static android.app.ProgressDialog.show;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.tokuverseproject.Activity.HomeActivity;
import com.example.tokuverseproject.Activity.SignUp;
import com.example.tokuverseproject.Model.Hero;
import com.example.tokuverseproject.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class ServerHandler {

    String Ip = //"10.40.169.58";
    "192.168.1.27";
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://"+Ip+"/Server/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    API Api = retrofit.create(API.class);
    public void getUser()
    {
        Call<List<User>> call = Api.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                for(int i = 0; i < userList.size(); i++)
                {
                    Log.d("sucess", userList.get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });


    }

    public interface getHero_CallBack
    {
        void onSuccess(List<Hero> heroList);
        void onFail(String message);
    }
    public void getHero(getHero_CallBack callBack)
    {
        Call<List<Hero>> call = Api.getHero();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();
                try
                {
                    callBack.onSuccess(heroList);
                }
                catch (Exception e)
                {
                    callBack.onFail(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }

    public interface LoginCallback {
        void onSuccess(String userId);
        void onFail(String message);
    }

    public void LogIn(String username, String password, LoginCallback callback)
    {
        Call<List<User>> call = Api.logIn(username, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response)
            {
                List<User> userList = response.body();
                try{

                    callback.onSuccess(userList.get(0).getId());
                }
                catch(Exception e) {
                    callback.onFail("Log in Failed");
                    Log.d("failed", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
                callback.onFail(t.getMessage());
            }
        });
    }

    public interface GetUserByID_CallBack {
        void onSuccess(User user);
        void onFail(String message);
    }

    public void GetUserByID(String id, GetUserByID_CallBack callBack)
    {
        Call<List<User>> call = Api.getUser_ByID(id);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                try
                {
                    callBack.onSuccess(userList.get(0));
                }
                catch(Exception e)
                {
                    Log.d("Failed", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }
    public void signUp(User user, Activity signUpActivity)
    {
        Call<Void> call = Api.signUpAction(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone_number());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Do something if success.

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }

    public interface selectHero_CallBack{
        void onSuccess();
        void onFail(String message);
    }
    public void selectHero(String user_id, String hero_id, selectHero_CallBack callBack)
    {
        Call<Void> call = Api.selecHero(user_id, hero_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callBack.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}

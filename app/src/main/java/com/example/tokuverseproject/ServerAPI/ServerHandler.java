package com.example.tokuverseproject.ServerAPI;

import static android.app.ProgressDialog.show;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.tokuverseproject.Activity.HomeActivity;
import com.example.tokuverseproject.Activity.SignUp;
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
    Context context;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://tokutech490.000webhostapp.com/")
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
                    Log.d("sucess", userList.get(i).getPassword());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });


    }

    public String LogIn(Activity mainActivity)
    {
        Call<List<User>> call = Api.logIn("Test", "123465");
        final String[] userId = new String[1];
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response)
            {
                try
                {
                    List<User> userList = response.body();
                    Toast.makeText(mainActivity, "Log In Sucess",
                            Toast.LENGTH_LONG).show();

                    userId[0] = userList.get(0).getId();
                    Log.d("sucess", userId[0]);

                }
                catch(Exception e)
                {
                    Log.d("failed", e.getMessage());
                    Toast.makeText(mainActivity, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
        return  userId[0];
    }

    public void signUp(User user, Activity signUpActivity)
    {
        Call<Void> call = Api.signUpAction(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone_number());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Do something if success.
                Toast.makeText(signUpActivity, "Sign up sucessful",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }
}

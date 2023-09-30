package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn_GoToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_GoToSignUp = findViewById(R.id.btn_GoToSignUp);
        btn_GoToSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://192.168.1.37/Server/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API Api = retrofit.create(API.class);
                Call<List<User>> call = Api.getUser();
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                         List<User> userList = response.body();
                         for(User user : userList)
                         {
                         }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("failed", t.getMessage());
                    }
                });
            }
        });
    }
    void gotoSignUp()
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.API;
import com.example.tokuverseproject.ServerAPI.ServerHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public User user;
    Button btn_GoToSignUp;
    Button btn_Login;
    EditText txt_Username, txt_Password;
    boolean showPassword;


    ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_GoToSignUp = findViewById(R.id.btn_GoToSignUp);
        btn_Login = findViewById(R.id.btn_Login);
        txt_Username = findViewById(R.id.txt_Username);
        txt_Password = findViewById(R.id.txt_Password);


        btn_GoToSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Test();
            }
        });
        btn_Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                logIn();
            }
        });
    }

    void Test()
    {
        serverHandler.getUser();
    }
    void gotoSignUp()
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    void logIn()
    {
        String userName = txt_Username.getText().toString();
        String passWord = txt_Password.getText().toString();
        serverHandler.LogIn(userName, passWord, new ServerHandler.LoginCallback()
        {
            @Override
            public void onSuccess(String userId)
            {
                Log.d("success", userId);
                Toast.makeText(MainActivity.this, "Log in sucessful",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("userID", userId);
                startActivity(intent);
            }
            @Override
            public void onFail(String message)
            {
                Toast.makeText(MainActivity.this, message,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
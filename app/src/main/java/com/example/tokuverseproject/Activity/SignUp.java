package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;

public class SignUp extends AppCompatActivity {
    Button btn_SignUp;
    TextView txt_Username;
    TextView txt_Password;
    TextView txt_Email;
    TextView txt_PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_SignUp = findViewById(R.id.btn_SignUp);


        txt_Username = findViewById(R.id.txt_Email);
        txt_Password = findViewById(R.id.txt_Password);
        txt_Email = findViewById(R.id.txt_Email);
        txt_PhoneNumber = findViewById(R.id.txt_PhoneNumber);
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }

    @SuppressLint("NotConstructor")
    void SignUp()
    {
        String userName = txt_Username.getText().toString();
        String password = txt_Password.getText().toString();
        String email = txt_Email.getText().toString();
        String phoneNumber = txt_PhoneNumber.getText().toString();

    }
}
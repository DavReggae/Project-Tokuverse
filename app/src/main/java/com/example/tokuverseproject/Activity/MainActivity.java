package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tokuverseproject.R;

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
                gotoSignUp();
            }
        });
    }
    void gotoSignUp()
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
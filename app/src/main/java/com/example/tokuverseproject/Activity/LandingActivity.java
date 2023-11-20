package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tokuverseproject.R;

public class LandingActivity extends AppCompatActivity {

    Button btn_Test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        btn_Test = findViewById(R.id.btn_Test);
        btn_Test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                try {

                }
                catch(Exception e)
                {
                    Log.d("Tag", e.getMessage());
                }
                Log.d("Tag", " Tesst Message");
                Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
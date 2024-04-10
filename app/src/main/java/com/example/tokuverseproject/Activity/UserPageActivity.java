package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class UserPageActivity extends AppCompatActivity {

    ImageView btn_UserPageBack, img_UserPage_Avatar, img_UserPage_HeroImage;
    TextView lbl_UserPage_Username, lbl_UserPage_HeroLevel;
    Button btn_UserPage_Fight;
    User user, clicked_user;

    ProgressBar loadingBar_UserPage;
    ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        user = (User) getIntent().getSerializableExtra("user");
        clicked_user = (User) getIntent().getSerializableExtra("clicked_user");

        img_UserPage_Avatar = findViewById(R.id.img_UserPage_Avatar);
        img_UserPage_HeroImage = findViewById(R.id.img_UserPage_HeroImage);
        lbl_UserPage_Username = findViewById(R.id.lbl_UserPage_Username);
        lbl_UserPage_HeroLevel = findViewById(R.id.lbl_UserPage_HeroLevel);
        btn_UserPage_Fight = findViewById(R.id.btn_UserPage_Fight);
        loadingBar_UserPage = findViewById(R.id.loadingBar_UserPage);

        showLoading();
        serverHandler.LoadImageFromURL(clicked_user.getAvatar(), img_UserPage_Avatar);
        serverHandler.LoadImageFromURL(clicked_user.getClass_HeroDetails().getClass_Hero().getFull_pic(), img_UserPage_HeroImage);
        lbl_UserPage_Username.setText(clicked_user.getUsername());
        lbl_UserPage_HeroLevel.setText("Level: " + clicked_user.getClass_HeroDetails().getLevel());
        dismissLoading();



        btn_UserPageBack = findViewById(R.id.btn_UserPageBack);
        btn_UserPageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPageActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("scene", "1");
                startActivity(intent);
            }
        });

    }

    public void showLoading() {
        loadingBar_UserPage.setVisibility(View.VISIBLE);
    }

    // Method to dismiss loading screen
    public void dismissLoading() {
        loadingBar_UserPage.setVisibility(View.GONE);
    }
}
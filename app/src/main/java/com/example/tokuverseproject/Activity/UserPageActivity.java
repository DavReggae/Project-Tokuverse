package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class UserPageActivity extends AppCompatActivity {

    ImageView img_Avatar, btn_back;
    TextView lbl_UserName;
    Button btn_goToHeroInfo;
    String userId;
    String click_userId;
    ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getStringExtra("userID");
        click_userId = getIntent().getStringExtra("cliked_userID");
        setContentView(R.layout.activity_user_page);
        img_Avatar = findViewById(R.id.img_UserPageAvatar);
        lbl_UserName = findViewById(R.id.lbl_UserPageName);
        btn_back = findViewById(R.id.btn_UserPageBack);
        btn_goToHeroInfo = findViewById(R.id.btn_UserPageHeroInfo);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });
        serverHandler.GetUserByID(click_userId, new ServerHandler.GetUserByID_CallBack() {
            @Override
            public void onSuccess(User user) {
                serverHandler.LoadImageFromURL(user.getAvatar(), img_Avatar);
                lbl_UserName.setText(user.getUsername());
            }
            @Override
            public void onFail(String message) {

            }
        });
        btn_goToHeroInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHeroInfo();
            }
        });
    }

    void gotoHeroInfo()
    {
        Intent intent = new Intent(this, HeroInfoActivity.class);
        intent.putExtra("userID", userId);
        intent.putExtra("cliked_userID", click_userId);
        startActivity(intent);
    }
    void backToHome()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("userID", userId);
        startActivity(intent);
    }
}
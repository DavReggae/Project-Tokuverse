package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class CreatePostActivity extends AppCompatActivity {

    ImageView backButton, img_Avatar;
    TextView lbl_UserName, txt_CreatePostContent;
    Button btn_PostAction;
    ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        backButton = findViewById(R.id.btn_CreatePostBack);
        img_Avatar = findViewById(R.id.imgView_CreatePostUserAvatar);
        lbl_UserName = findViewById(R.id.lbl_CreatePostUserName);
        txt_CreatePostContent = findViewById(R.id.txt_CreatePostContent);
        btn_PostAction = findViewById(R.id.btn_PostAction);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        String userId = getIntent().getStringExtra("userID");
        serverHandler.GetUserByID(userId, new ServerHandler.GetUserByID_CallBack() {
            @Override
            public void onSuccess(User user) {
                lbl_UserName.setText(user.getUsername());
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
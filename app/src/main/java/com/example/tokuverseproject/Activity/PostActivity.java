package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tokuverseproject.Model.NewFeeds;
import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class PostActivity extends AppCompatActivity {

    ServerHandler serverHandler =  new ServerHandler();
    ImageView img_ClickedUserAvatar;
    TextView lbl_ClickedUserName, lbl_Post_Content, lbl_Post_LikeCount, lbl_Post_CommentCount;
    Button btn_Post_Like, btn_Post_Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        img_ClickedUserAvatar = findViewById(R.id.img_ClickedUserAvatar);
        lbl_ClickedUserName = findViewById(R.id.lbl_ClickedUserName);
        lbl_Post_Content = findViewById(R.id.lbl_Post_Content);
        lbl_Post_LikeCount = findViewById(R.id.lbl_Post_LikeCount);
        lbl_Post_CommentCount = findViewById(R.id.lbl_Post_CommentCount);
        btn_Post_Like = findViewById(R.id.btn_Post_Like);
        btn_Post_Comment = findViewById(R.id.btn_Post_Comment);
        String cliked_userID = getIntent().getStringExtra("cliked_userID");
        String user_ID= getIntent().getStringExtra("userID");
        String post_ID = getIntent().getStringExtra("postID");
        serverHandler.GetUserByID(cliked_userID, new ServerHandler.GetUserByID_CallBack() {
            @Override
            public void onSuccess(User user) {
                serverHandler.LoadImageFromURL(user.getAvatar(), img_ClickedUserAvatar);
                lbl_ClickedUserName.setText(user.getUsername());
            }

            @Override
            public void onFail(String message) {

            }
        });
        serverHandler.getNewFeed_ByID(post_ID, new ServerHandler.getNewFeed_ByID_CallBack() {
            @Override
            public void onSuccess(NewFeeds newFeed) {
                lbl_Post_Content.setText(newFeed.getContent());
                ser
            }

            @Override
            public void onFailed(String message) {

            }
        });

    }
}
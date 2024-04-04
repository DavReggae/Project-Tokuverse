package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tokuverseproject.Model.JSON_MESSAGE;
import com.example.tokuverseproject.Model.Like;
import com.example.tokuverseproject.Model.NewFeeds;
import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class PostActivity extends AppCompatActivity {

    ServerHandler serverHandler =  new ServerHandler();
    ImageView img_ClickedUserAvatar, btn_Back;
    TextView lbl_ClickedUserName, lbl_Post_Content, lbl_Post_LikeCount, lbl_Post_CommentCount, txt_Comment;
    Button btn_Post_Like, btn_Post_Comment, btn_CreateComment;

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
        btn_Back = findViewById(R.id.btn_PostView_Back);
        btn_CreateComment = findViewById(R.id.btn_CreateComment);
        txt_Comment = findViewById(R.id.txt_Comment);
        String cliked_userID = getIntent().getStringExtra("cliked_userID");
        String user_ID= getIntent().getStringExtra("userID");
        String post_ID = getIntent().getStringExtra("postID");
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                serverHandler.getLike_ByUserId_And_NewsFeedId(post_ID, user_ID, new ServerHandler.getLike_ByUserId_And_NewsFeedId_CallBack() {
                    @Override
                    public void onSuccess(Like like) {
                        btn_Post_Like.setTextColor(Color.RED);
                        btn_Post_Like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_liked_icon_24,0 ,0 ,0);
                        if(Integer.parseInt(newFeed.getLike_count()) == 1)
                        {
                            lbl_Post_LikeCount.setText("  You");
                        }
                        else
                        {
                            lbl_Post_LikeCount.setText("  You and " + newFeed.getLike_count() + " others");
                        }
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
                btn_Post_Like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        serverHandler.like_Acion(post_ID, user_ID, new ServerHandler.LikeAction_CallBack() {
                            @Override
                            public void onSuccess(JSON_MESSAGE jsonMessage) {
                                JSON_MESSAGE message = jsonMessage;
                                if(message.getMessage().equals("0") == false)
                                {
                                    btn_Post_Like.setTextColor(Color.RED);
                                    btn_Post_Like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_liked_icon_24,0 ,0 ,0);
                                    Toast.makeText(PostActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                                    if(Integer.parseInt(newFeed.getLike_count()) == 0)
                                    {
                                        lbl_Post_LikeCount.setText("  You");
                                    }
                                    else
                                    {
                                        lbl_Post_LikeCount.setText("  You and " + newFeed.getLike_count() + " others");
                                    }
                                }
                            }

                            @Override
                            public void onFailed(String message) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onFailed(String message) {

            }
        });
        btn_CreateComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = txt_Comment.getText().toString();
                serverHandler.creaetComment_Action(user_ID, content, post_ID, new ServerHandler.CreateComment_CallBack() {
                    @Override
                    public void onSuccess(JSON_MESSAGE message) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        Toast.makeText(PostActivity.this, message.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
            }
        });


    }
}
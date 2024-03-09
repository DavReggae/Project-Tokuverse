package com.example.tokuverseproject.Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tokuverseproject.Activity.PostActivity;
import com.example.tokuverseproject.Activity.UserPageActivity;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.List;

public class NewFeedCustomBase extends BaseAdapter {
    LayoutInflater inflater;
    ServerHandler serverHandler = new ServerHandler();

    String user_id;

    List<NewFeeds>  newFeedsList;

    public NewFeedCustomBase(Context ctx, List<NewFeeds> newFeedsList, String user_id)
    {
        this.newFeedsList = newFeedsList;
        this.user_id = user_id;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return newFeedsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.new_feeds_item_list, null);
        ImageView img_PostUserAvatar = view.findViewById(R.id.img_PostUserAvatar);
        TextView lbl_PostUserName = view.findViewById(R.id.lbl_PostUserName);
        TextView lbl_PostConent = view.findViewById(R.id.lbl_Content);
        TextView lbl_likeCount = view.findViewById(R.id.lbl_LikeCount);
        TextView lbl_commentCount = view.findViewById(R.id.lbl_CommentCount);
        Button btn_Like = view.findViewById(R.id.btn_Like);
        NewFeeds newFeeds = newFeedsList.get(i);
        serverHandler.LoadImageFromURL(newFeeds.getUser_avatar(), img_PostUserAvatar);
        lbl_PostUserName.setText(newFeeds.getUser_name());
        lbl_PostConent.setText(newFeeds.getContent());
        lbl_likeCount.setText("   " + newFeeds.getLike_count());
        lbl_commentCount.setText(newFeeds.getComment_count() +"   ");
        boolean isLike = false;
        lbl_PostUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    Intent intent = new Intent(inflater.getContext(), UserPageActivity.class);
                    intent.putExtra("userID", user_id);
                    intent.putExtra("cliked_userID", newFeeds.user_id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    inflater.getContext().startActivity(intent);
                }
                catch(Exception e)
                {
                    Log.d("User name clicked error", e.getMessage());
                }
            }
        });

        serverHandler.getLike_ByUserId_And_NewsFeedId(newFeeds.id, user_id, new ServerHandler.getLike_ByUserId_And_NewsFeedId_CallBack() {
            @Override
            public void onSuccess(Like like)
            {
                if(like.is_liked.equals("1"))
                {
                    btn_Like.setTextColor(Color.RED);
                    btn_Like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_liked_icon_24,0 ,0 ,0);
                    if(Integer.parseInt(newFeeds.like_count) == 1)
                    {
                        lbl_likeCount.setText("  You");
                    }
                    else
                    {
                        lbl_likeCount.setText("  You and " + newFeeds.like_count + " others");
                    }
                }
            }

            @Override
            public void onFailed(String message) {

            }
        });
        btn_Like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverHandler.like_Acion(newFeeds.id, user_id, new ServerHandler.LikeAction_CallBack() {
                    @Override
                    public void onSuccess(JSON_MESSAGE jsonMessage) {
                        JSON_MESSAGE message = jsonMessage;
                        if(message.getMessage().equals("0") == false)
                        {
                            Toast.makeText(inflater.getContext(), message.getMessage(),Toast.LENGTH_SHORT).show();
                            btn_Like.setTextColor(Color.RED);
                            btn_Like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_liked_icon_24,0 ,0 ,0);
                            if(Integer.parseInt(newFeeds.like_count) == 0)
                            {
                                lbl_likeCount.setText("  You");
                            }
                            else
                            {
                                lbl_likeCount.setText("  You and " + newFeeds.like_count + " others");
                            }
                        }
                    }
                    @Override
                    public void onFailed(String message) {

                    }
                });
                serverHandler.getLike_ByUserId_And_NewsFeedId(newFeeds.id, user_id, new ServerHandler.getLike_ByUserId_And_NewsFeedId_CallBack() {
                    @Override
                    public void onSuccess(Like like)
                    {
                        if(like.is_liked.equals("1"))
                        {
                            btn_Like.setTextColor(Color.BLACK);
                            btn_Like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_like_icon_24,0 ,0 ,0);
                        }
                        else
                        {
                            btn_Like.setTextColor(Color.RED);
                            btn_Like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_liked_icon_24,0 ,0 ,0);
                        }
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent(inflater.getContext(), PostActivity.class);
                    intent.putExtra("userID", user_id);
                    intent.putExtra("cliked_userID", newFeeds.user_id);
                    intent.putExtra("postID", newFeeds.id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    inflater.getContext().startActivity(intent);
                }
                catch(Exception e)
                {
                    Log.d("User name clicked error", e.getMessage());
                }
            }
        });
        return view;
    }
}

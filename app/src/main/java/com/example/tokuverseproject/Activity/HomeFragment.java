package com.example.tokuverseproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tokuverseproject.Model.NewFeeds;
import com.example.tokuverseproject.Model.Post;
import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    Bundle bundle = new Bundle();
    String userId = "0";

    ImageView img_Avatar;
    Button btn_CreatePost;
    TextView lbl_UserName;
    ServerHandler serverHandler = new ServerHandler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if(bundle != null)
        {
            userId = bundle.getString("userID");
        }
        Log.d("Home UserID",userId);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lbl_UserName = view.findViewById(R.id.lbl_HomeUserName);
        img_Avatar = view.findViewById(R.id.image_HomeUserAvatar);
        btn_CreatePost = view.findViewById(R.id.btn_CreatePost);

        serverHandler.GetUserByID(userId, new ServerHandler.GetUserByID_CallBack() {
            @Override
            public void onSuccess(User user) {
                Log.d("onSuccess", user.getUsername());
                lbl_UserName.setText(user.getUsername());
                serverHandler.LoadImageFromURL(user.getAvatar(), img_Avatar);
            }

            @Override
            public void onFail(String message) {

            }
        });
        List<Post> postList = new ArrayList<>();
        serverHandler.getNewFeedAction(new ServerHandler.GetNewFeeds_CallBack() {
            @Override
            public void onSuccess(List<NewFeeds> newFeedsList) {
                for(int i = 0; i < newFeedsList.size(); i++)
                {
                    postList.get(i).setId(newFeedsList.get(i).getId());
                    postList.get(i).setUser_id(newFeedsList.get(i).getId());
                    postList.get(i).setContent(newFeedsList.get(i).getContent());
                    postList.get(i).setDate_post(newFeedsList.get(i).getDate_post());
                    postList.get(i).setLike_count(newFeedsList.get(i).getLike_count());
                    postList.get(i).setComment_count(newFeedsList.get(i).getComment_count());
                    int final_i = i;
                    serverHandler.GetUserByID(postList.get(i).getId(), new ServerHandler.GetUserByID_CallBack() {
                        @Override
                        public void onSuccess(User user) {
                            postList.get(final_i).setUser_name(user.getUsername());
                            postList.get(final_i).setUser_image(user.getAvatar());
                        }

                        @Override
                        public void onFail(String message) {

                        }
                    });
                }
            }
            @Override
            public void onFailed(String message) {

            }
        });
        for(int i = 0; i < postList.size(); i++)
        {

        }

        btn_CreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPostAction();
            }
        });
        return view;

    }

    void createPostAction()
    {
        Intent intent = new Intent(getActivity(), CreatePostActivity.class);
        intent.putExtra("userID", userId);
        getActivity().startActivity(intent);
    }
}
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

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;


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
package com.example.tokuverseproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tokuverseproject.Model.NewFeedCustomBase;
import com.example.tokuverseproject.Model.NewFeeds;
import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.List;


public class HomeFragment extends Fragment {

    Bundle bundle = new Bundle();
    User user;

    ImageView img_Avatar;
    Button btn_CreatePost;
    TextView lbl_UserName;
    ServerHandler serverHandler = new ServerHandler();
    ListView listView_NewFeeds;

    ProgressBar loadingBar_Home;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if(bundle != null)
        {
            user = (User) bundle.getSerializable("user");
        }
        Log.d("Home UserID",user.getId());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lbl_UserName = view.findViewById(R.id.lbl_HomeUserName);
        img_Avatar = view.findViewById(R.id.image_HomeUserAvatar);
        btn_CreatePost = view.findViewById(R.id.btn_CreatePost);
        listView_NewFeeds = view.findViewById(R.id.listView_Post);
        loadingBar_Home = view.findViewById(R.id.loadingBar_Home);

        lbl_UserName.setText(user.getUsername());
        serverHandler.LoadImageFromURL(user.getAvatar(), img_Avatar);

        Context appContext = requireContext().getApplicationContext();
        showLoading();
        serverHandler.getNewFeedAction(new ServerHandler.GetNewFeeds_CallBack() {
            @Override
            public void onSuccess(List<NewFeeds> newFeedsList) {
                NewFeedCustomBase newFeedCustomBase = new NewFeedCustomBase(appContext, newFeedsList, user.getId());
                listView_NewFeeds.setAdapter(newFeedCustomBase);
                dismissLoading();
            }

            @Override
            public void onFailed(String message) {
                dismissLoading();
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

    // Method to show loading screen
    private void showLoading() {
        loadingBar_Home.setVisibility(View.VISIBLE);
    }

    // Method to dismiss loading screen
    private void dismissLoading() {
        loadingBar_Home.setVisibility(View.GONE);
    }
    void createPostAction()
    {
        Intent intent = new Intent(getActivity(), CreatePostActivity.class);
        intent.putExtra("userID", user.getId());
        getActivity().startActivity(intent);
    }
}
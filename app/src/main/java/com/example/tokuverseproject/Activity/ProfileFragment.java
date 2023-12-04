package com.example.tokuverseproject.Activity;

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


public class ProfileFragment extends Fragment {

    ServerHandler serverHandler = new ServerHandler();
    Bundle bundle = new Bundle();
    String userId = "0";
    ImageView img_ProfileUserAvatar;
    TextView lbl_Username, lbl_Email, lbl_PhoneNumber, lbl_coins;
    Button btn_HeroInfo;
    Button btn_LogOut;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if(bundle != null)
        {
            userId = bundle.getString("userID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        lbl_Username = view.findViewById(R.id.lbl_Username);
        lbl_Email = view.findViewById(R.id.lbl_Email);
        lbl_PhoneNumber = view.findViewById(R.id.lbl_MobilePhone);
        lbl_coins = view.findViewById(R.id.lbl_coins);
        img_ProfileUserAvatar = view.findViewById(R.id.img_ProfileUserAvatar);
        btn_HeroInfo = view.findViewById(R.id.btn_GoToHero);
        try
        {
            serverHandler.GetUserByID(userId, new ServerHandler.GetUserByID_CallBack() {
                @Override
                public void onSuccess(User user) {
                    Log.d("Success", user.getId());
                    lbl_Username.setText("  " + user.getUsername());
                    lbl_Email.setText("  " + user.getEmail());
                    lbl_PhoneNumber.setText("  " + user.getPhone_number());
                    lbl_coins.setText("  " + user.getCoins());
                    serverHandler.LoadImageFromURL(user.getAvatar(), img_ProfileUserAvatar);
                    btn_HeroInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {
                            gotoSelectHero();
                        }
                    });
                }

                @Override
                public void onFail(String message)
                {

                }
            });
        }
        catch(Exception e)
        {
            Log.d("failed", e.getMessage());
        }
        return view;
    }

    void gotoSelectHero()
    {
        serverHandler.GetUserByID(userId, new ServerHandler.GetUserByID_CallBack() {
            @Override
            public void onSuccess(User user) {
                String hero_details_id = user.getHero_id();
                if(hero_details_id.equals("0"))
                {
                    Intent intent = new Intent(getActivity(), SelectHeroActivity.class);
                    intent.putExtra("userID", userId);
                    getActivity().startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getActivity(), HeroInfoActivity.class);
                    intent.putExtra("user_id", userId);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onFail(String message) {

            }
        });

    }
}
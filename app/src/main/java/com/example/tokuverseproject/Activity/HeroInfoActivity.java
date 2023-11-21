package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tokuverseproject.Model.Hero;
import com.example.tokuverseproject.Model.HeroDetails;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class HeroInfoActivity extends AppCompatActivity {

    TextView lbl_HeroName, lbl_Attack, lbl_Defend, lbl_Heath;
    ImageView btnBack, img_HeroInfoFullPic;

    ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_info);
        lbl_HeroName = findViewById(R.id.lbl_HeroInfoName);
        lbl_Attack = findViewById(R.id.lbl_attackPoint);
        lbl_Defend = findViewById(R.id.lbl_defendPoint);
        lbl_Heath = findViewById(R.id.lbl_healthPoint);
        btnBack = findViewById(R.id.imgView_BackBtn);
        img_HeroInfoFullPic = findViewById(R.id.imgView_HeroInfoFullPic);
        String userID = getIntent().getStringExtra("user_id");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        serverHandler.getHeroDetails_ByID(userID, new ServerHandler.getHeroDetails_ByID_callBack() {
            @Override
            public void onSuccess(HeroDetails heroDetails) {
                lbl_Attack.setText("  " + heroDetails.getAttach_point());
                lbl_Defend.setText("  " + heroDetails.getDefense_point());
                lbl_Heath.setText("  " + heroDetails.getHealth_point());
                serverHandler.getHero_ByID(heroDetails.getHero_id(), new ServerHandler.CallBack() {
                    @Override
                    public void getHero_ByID_Success(Hero hero) {
                        lbl_HeroName.setText(hero.getHero_name());
                        serverHandler.LoadImageFromURL(hero.getFull_pic(),img_HeroInfoFullPic);
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
            }
        });
    }
}
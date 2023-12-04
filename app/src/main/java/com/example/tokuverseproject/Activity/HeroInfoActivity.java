package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tokuverseproject.Model.Hero;
import com.example.tokuverseproject.Model.HeroDetails;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class HeroInfoActivity extends AppCompatActivity {

    TextView lbl_HeroName, lbl_Attack, lbl_Defend, lbl_Heath, lbl_Level, lbl_exp, lbl_Attribute;
    ImageView btnBack, img_HeroInfoFullPic;

    ProgressBar exp_bar;
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
        lbl_Level = findViewById(R.id.lbl_level);
        lbl_exp = findViewById(R.id.lbl_exp);
        lbl_Attribute = findViewById(R.id.lbl_Attribute);
        exp_bar = findViewById(R.id.exp_bar);
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
                lbl_exp.setText(heroDetails.getExp() + "/" + heroDetails.getMax_exp());
                lbl_Level.setText("Level " + heroDetails.getLevel());
                lbl_Attribute.setText("Attribute: " + heroDetails.getAttribute_point() + " points");
                int current_exp = Integer.parseInt(heroDetails.getExp().toString());
                int max_exp = Integer.parseInt(heroDetails.getMax_exp().toString());
                exp_bar.setMax(max_exp);
                exp_bar.setProgress(current_exp, true);
                Log.d("EXP", Integer.toString(current_exp) + "/" + Integer.toString(max_exp));
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
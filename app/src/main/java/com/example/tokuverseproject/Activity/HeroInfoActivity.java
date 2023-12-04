package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    String userID, click_userId;
    ProgressBar exp_bar;
    ServerHandler serverHandler = new ServerHandler();

    ImageButton btn_AttackPlus, btn_AttackMinus, btn_DefendPlus, btn_DefendMinus, btn_HealthPlus, btn_HealthMinus;
    Button btn_Features;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_info);
        lbl_HeroName = findViewById(R.id.lbl_HeroInfoName);
        lbl_Attack = findViewById(R.id.lbl_attackPoint);
        lbl_Defend = findViewById(R.id.lbl_defendPoint);
        lbl_Heath = findViewById(R.id.lbl_healthPoint);
        btnBack = findViewById(R.id.imgView_BackBtn);
        btn_Features = findViewById(R.id.btn_HeroInfoFeatures);
        img_HeroInfoFullPic = findViewById(R.id.imgView_HeroInfoFullPic);
        lbl_Level = findViewById(R.id.lbl_level);
        lbl_exp = findViewById(R.id.lbl_exp);
        lbl_Attribute = findViewById(R.id.lbl_Attribute);
        btn_AttackPlus = findViewById(R.id.btn_AttackPlus);
        btn_AttackMinus = findViewById(R.id.btn_AttackMinus);
        btn_DefendPlus = findViewById(R.id.btn_DefendPlus);
        btn_DefendMinus = findViewById(R.id.btn_DefendMinus);
        btn_HealthPlus = findViewById(R.id.btn_HealthPlus);
        btn_HealthMinus = findViewById(R.id.btn_HealthMinus);

        exp_bar = findViewById(R.id.exp_bar);
        userID = getIntent().getStringExtra("user_id");
        click_userId = getIntent().getStringExtra("cliked_userID");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if(click_userId == null || click_userId == "")
        {
            LoadHeroInfo(userID);
        }
        else
        {
            btn_Features.setText("Fight");
            btn_AttackPlus.setVisibility(View.GONE);
            btn_AttackMinus.setVisibility(View.GONE);
            btn_DefendPlus.setVisibility(View.GONE);
            btn_DefendMinus.setVisibility(View.GONE);
            btn_HealthPlus.setVisibility(View.GONE);
            btn_HealthMinus.setVisibility(View.GONE);
            LoadHeroInfo(click_userId);
            btn_Features.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }
    void LoadHeroInfo(String id)
    {
        serverHandler.getHeroDetails_ByID(id, new ServerHandler.getHeroDetails_ByID_callBack() {
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
                    public void onFailed(String message)
                    {

                    }
                });
            }
        });
    }
}


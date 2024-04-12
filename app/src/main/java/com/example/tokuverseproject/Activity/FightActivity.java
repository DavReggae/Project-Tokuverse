package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

public class FightActivity extends AppCompatActivity {

    User user, clicked_user;
    ImageView img_Figh_UserHero;
    TextView lbl_Fight_Username;
    TextView lbl_Fight_UserAttack;
    TextView lbl_Fight_UserDefense;
    TextView lbl_Fight_UserHealth;
    TextView lbl_Fight_UserLevel;

    ImageView img_Figh_ClickedUserHero;
    TextView lbl_Fight_ClickedUserName;
    TextView lbl_Fight_ClickedUserAttack;
    TextView lbl_Fight_ClickedUserDefense;
    TextView lbl_Fight_ClickedUserHealth;
    TextView lbl_Fight_ClickedUserLevel;
    Button btn_FightAction;
    ProgressBar loadingBar_Fight;

    ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        user = (User) getIntent().getSerializableExtra("user");
        clicked_user = (User) getIntent().getSerializableExtra("clicked_user");

        img_Figh_UserHero = findViewById(R.id.img_Figh_UserHero);
        lbl_Fight_Username = findViewById(R.id.lbl_Fight_Username);
        lbl_Fight_UserAttack = findViewById(R.id.lbl_Fight_UserAttack);
        lbl_Fight_UserDefense = findViewById(R.id.lbl_Fight_UserDefense);
        lbl_Fight_UserHealth = findViewById(R.id.lbl_Fight_UserHealth);
        lbl_Fight_UserLevel = findViewById(R.id.lbl_Fight_UserLevel);

        img_Figh_ClickedUserHero = findViewById(R.id.img_Figh_ClickedUserHero);
        lbl_Fight_ClickedUserName = findViewById(R.id.lbl_Fight_ClickedUserName);
        lbl_Fight_ClickedUserAttack = findViewById(R.id.lbl_Fight_ClickedUserAttack);
        lbl_Fight_ClickedUserDefense = findViewById(R.id.lbl_Fight_ClickedUserDefense);
        lbl_Fight_ClickedUserHealth = findViewById(R.id.lbl_Fight_ClickedUserHealth);
        lbl_Fight_ClickedUserLevel = findViewById(R.id.lbl_Fight_ClickedUserLevel);
        btn_FightAction = findViewById(R.id.btn_FightAction);

        loadingBar_Fight = findViewById(R.id.loadingBar_Fight);

        showLoading();
        serverHandler.LoadImageFromURL(user.getClass_HeroDetails().getClass_Hero().getFull_pic(), img_Figh_UserHero);
        serverHandler.LoadImageFromURL(clicked_user.getClass_HeroDetails().getClass_Hero().getFull_pic(), img_Figh_ClickedUserHero);
        dismissLoading();

        lbl_Fight_Username.setText(user.getUsername());
        lbl_Fight_UserAttack.setText("  " + user.getClass_HeroDetails().getAttach_point());
        lbl_Fight_UserDefense.setText("  " + user.getClass_HeroDetails().getDefense_point());
        lbl_Fight_UserHealth.setText("  " + user.getClass_HeroDetails().getHealth_point());
        lbl_Fight_UserLevel.setText("Level: " + user.getClass_HeroDetails().getLevel());

        lbl_Fight_ClickedUserName.setText(clicked_user.getUsername());
        lbl_Fight_ClickedUserAttack.setText("  " + clicked_user.getClass_HeroDetails().getAttach_point());
        lbl_Fight_ClickedUserDefense.setText("  " + clicked_user.getClass_HeroDetails().getDefense_point());
        lbl_Fight_ClickedUserHealth.setText("  " + clicked_user.getClass_HeroDetails().getHealth_point());
        lbl_Fight_ClickedUserLevel.setText("Level: " + clicked_user.getClass_HeroDetails().getLevel());

        btn_FightAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer user_Attack = Integer.parseInt(user.getClass_HeroDetails().getAttach_point());
                Integer user_Defense = Integer.parseInt(user.getClass_HeroDetails().getDefense_point());
                Integer user_Health = Integer.parseInt(user.getClass_HeroDetails().getHealth_point());

                Integer clickedUser_Attack = Integer.parseInt(clicked_user.getClass_HeroDetails().getAttach_point());
                Integer clickedUser_Defense = Integer.parseInt(clicked_user.getClass_HeroDetails().getDefense_point());
                Integer clickedUser_Health = Integer.parseInt(clicked_user.getClass_HeroDetails().getHealth_point());
                Integer damage = 0;
                Integer total_reward = 0;
                Integer turn = 1;
                if(user_Attack < clickedUser_Defense)
                {
                    Toast.makeText(FightActivity.this, "You don't have enough damge",
                            Toast.LENGTH_LONG).show();
                }
                while (true)
                {
                    if(user_Health <= 0 || clickedUser_Health <= 0)
                    {
                        if(user_Health <= 0)
                        {
                            Toast.makeText(FightActivity.this, "YOU LOSE!!!!. YOU GOT " + total_reward + " coins",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(FightActivity.this, "YOU WIN!!!!. YOU GOT " + total_reward + " coins",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    }
                    else
                    {
                        if(turn == 1)
                        {
                            damage = user_Attack - clickedUser_Defense;
                            if(clickedUser_Health > damage)
                            {
                                clickedUser_Health = clickedUser_Health - damage;
                                total_reward += damage;
                            }
                            else
                            {
                                total_reward += clickedUser_Health;
                                clickedUser_Health = clickedUser_Health - damage;
                            }
                            turn = 2;
                        }
                        else if(turn == 2)
                        {
                            damage = clickedUser_Attack - user_Defense;
                            user_Health = user_Health - damage;
                            turn = 1;
                        }
                    }
                }
            }
        });
    }

    private void showLoading() {
        loadingBar_Fight.setVisibility(View.VISIBLE);
    }

    // Method to dismiss loading screen
    private void dismissLoading() {
        loadingBar_Fight.setVisibility(View.GONE);
    }
}
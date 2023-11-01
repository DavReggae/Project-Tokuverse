package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tokuverseproject.Model.Hero;
import com.example.tokuverseproject.Model.HeroCustomBase;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.ArrayList;
import java.util.List;

public class SelectHeroActivity extends AppCompatActivity {

    ImageView btn_Back;
    ServerHandler serverHandler = new ServerHandler();

    Button btn_Save;
    ListView listView_Hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hero);
        listView_Hero = findViewById(R.id.hero_listView);
        btn_Back = findViewById(R.id.btn_Back);
        btn_Save = findViewById(R.id.btn_Save);
        String userId = getIntent().getStringExtra("userID");

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        serverHandler.getHero(new ServerHandler.getHero_CallBack() {
            @Override
            public void onSuccess(List<Hero> heroList) {
                String[] hero_name = new String[heroList.size()];
                String[] hero_description = new String[heroList.size()];
                String[] hero_pic = new String[heroList.size()];
                for(int i = 0; i < heroList.size(); i++)
                {
                    Log.d("Hero List", heroList.get(i).getHero_name());
                    hero_name[i] = heroList.get(i).getHero_name();
                    hero_description[i] = heroList.get(i).getDescription();
                    hero_pic[i] = heroList.get(i).getHero_pic();
                }
                HeroCustomBase heroCustomBaseAdapter = new HeroCustomBase(getApplicationContext(), hero_name, hero_description, hero_pic);
                listView_Hero.setAdapter(heroCustomBaseAdapter);

                listView_Hero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String select_heroID = heroList.get(i).getId();
                        for(int j = 0; j < adapterView.getChildCount(); j++)
                        {
                            if(i == j)
                            {
                                adapterView.getChildAt(j).setBackgroundColor(Color.CYAN);

                            }
                            else
                            {
                                adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                            }
                        }
                        btn_Save.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view) {
                                serverHandler.selectHero(userId, select_heroID, new ServerHandler.selectHero_CallBack() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onFail(String message) {

                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void onFail(String message)
            {

            }
        });
    }
}
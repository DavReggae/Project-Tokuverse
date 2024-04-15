package com.example.tokuverseproject.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.List;

public class FightRecordCustomBase extends BaseAdapter {
    LayoutInflater inflater;
    List<FightRecord> fightRecordList;
    ServerHandler serverHandler = new ServerHandler();

    public FightRecordCustomBase(Context ctx, List<FightRecord> fightRecordList)
    {
        this.fightRecordList = fightRecordList;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return fightRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.fight_record_item, null);
        TextView lbl_FightRecord_Turn = view.findViewById(R.id.lbl_FightRecord_Turn);
        TextView lbl_FightRecord_Damage = view.findViewById(R.id.lbl_FightRecord_Damge);
        TextView lbl_FightRecord_UserHP = view.findViewById(R.id.lbl_FightRecord_UserHP);
        TextView lbl_FightRecord_ClickedUserHP = view.findViewById(R.id.lbl_FightRecord_ClickedUserHP);
        ImageView img_FightRecord_User = view.findViewById(R.id.img_FightRecord_User);
        ImageView img_FightRecord_ClickedUser = view.findViewById(R.id.img_FightRecord_ClickedUser);
        ImageView img_AttackDirection = view.findViewById(R.id.img_AttackDirection);

        Integer turn_fight = i + 1;
        lbl_FightRecord_Turn.setText("Turn: " + turn_fight.toString());
        lbl_FightRecord_Damage.setText(fightRecordList.get(i).getDamage().toString() +" damage");
        if(fightRecordList.get(i).getUser_currentHP() <= 0)
        {
            lbl_FightRecord_UserHP.setText("0");
        }
        else
        {
            lbl_FightRecord_UserHP.setText(fightRecordList.get(i).getUser_currentHP().toString());
        }

        if(fightRecordList.get(i).getClickedUser_currentHP() <= 0)
        {
            lbl_FightRecord_ClickedUserHP.setText("0");
        }
        else
        {
            lbl_FightRecord_ClickedUserHP.setText(fightRecordList.get(i).getClickedUser_currentHP().toString());
        }

        serverHandler.LoadImageFromURL(fightRecordList.get(i).getUser_heroPic(), img_FightRecord_User);
        serverHandler.LoadImageFromURL(fightRecordList.get(i).getClickedUser_heroPIc(), img_FightRecord_ClickedUser);
        if(fightRecordList.get(i).getTurn() == 2)
        {
            int blueColor = Color.BLUE;
            img_AttackDirection.setImageResource(R.drawable.attack_direction);
            img_AttackDirection.setColorFilter(blueColor, PorterDuff.Mode.SRC_ATOP);
        }
        else if(fightRecordList.get(i).getTurn() == 1)
        {
            int redColor = Color.RED;
            img_AttackDirection.setScaleX(-1);
            img_AttackDirection.setImageResource(R.drawable.attack_direction);
            img_AttackDirection.setColorFilter(redColor, PorterDuff.Mode.SRC_ATOP);
        }
        return view;
    }
}

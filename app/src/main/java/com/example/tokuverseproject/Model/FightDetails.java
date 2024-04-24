package com.example.tokuverseproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FightDetails implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("fight_id")
    String fight_id;
    @SerializedName("turn")
    String turn;
    @SerializedName("damage")
    String damage;
    @SerializedName("user_currentHP")
    String user_currentHP;
    @SerializedName("fight_user_currentHP")
    String fight_user_currentHP;
}

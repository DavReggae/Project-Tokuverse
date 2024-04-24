package com.example.tokuverseproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FightHistory implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("fight_user_id")
    String fight_user_id;
    @SerializedName("rewards")
    String rewards;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFight_user_id() {
        return fight_user_id;
    }

    public void setFight_user_id(String fight_user_id) {
        this.fight_user_id = fight_user_id;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }
}

package com.example.tokuverseproject.Model;

import com.google.gson.annotations.SerializedName;

public class HeroDetails {
    @SerializedName("id")
    String id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("hero_id")
    String hero_id;
    @SerializedName("attack_point")
    String attach_point;
    @SerializedName("defense_point")
    String defense_point;
    @SerializedName("health_point")
    String health_point;

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

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public String getAttach_point() {
        return attach_point;
    }

    public void setAttach_point(String attach_point) {
        this.attach_point = attach_point;
    }

    public String getDefense_point() {
        return defense_point;
    }

    public void setDefense_point(String defense_point) {
        this.defense_point = defense_point;
    }

    public String getHealth_point() {
        return health_point;
    }

    public void setHealth_point(String health_point) {
        this.health_point = health_point;
    }
}

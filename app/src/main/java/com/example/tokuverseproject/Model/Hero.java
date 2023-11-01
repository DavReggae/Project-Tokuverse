package com.example.tokuverseproject.Model;
import com.google.gson.annotations.SerializedName;
public class Hero {
    @SerializedName("id")
    private String id;
    @SerializedName("hero_name")
    private String hero_name;
    @SerializedName("hero_type")
    private String hero_type;
    @SerializedName("description")
    private String description;
    @SerializedName("hero_pic")
    private String hero_pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHero_name() {
        return hero_name;
    }


    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public String getHero_type() {
        return hero_type;
    }

    public void setHero_type(String hero_type) {
        this.hero_type = hero_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHero_pic() {
        return hero_pic;
    }

    public void setHero_pic(String hero_pic) {
        this.hero_pic = hero_pic;
    }
}

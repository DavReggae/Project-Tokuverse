package com.example.tokuverseproject.Model;

public class FightRecord {
    User user;
    User clicked_user;
    Integer damage;
    Integer reward;
    Integer turn;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getClicked_user() {
        return clicked_user;
    }

    public void setClicked_user(User clicked_user) {
        this.clicked_user = clicked_user;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }
}

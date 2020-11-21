package com.juanbravo.retopokeapps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stat implements Serializable {

    @SerializedName("base_stat")
    private int baseStat;
    private StatType stat;

    public Stat() {
    }

    public Stat(int baseStat, StatType stat) {
        this.baseStat = baseStat;
        this.stat = stat;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public StatType getStat() {
        return stat;
    }

    public void setStat(StatType stat) {
        this.stat = stat;
    }
}

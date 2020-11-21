package com.juanbravo.retopokeapps.model;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private String name;
    private int id;
    private String dbId;
    private int height;
    private int weight;
    private Sprites sprites;
    private List<Stat> stats;
    private List<Type> types;
    private boolean isShiny;
    private long time;

    public Pokemon(String name, int id, int height, int weight, Sprites sprites, List<Stat> stats, List<Type> types) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.sprites = sprites;
        this.stats = stats;
        this.types = types;
        isShiny = false;
        time = 0;
    }

    public Pokemon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public boolean isShiny() {
        return isShiny;
    }

    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }
}
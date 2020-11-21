package com.juanbravo.retopokeapps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sprites implements Serializable {

    @SerializedName("front_default")
    private String front;

    @SerializedName("front_shiny")
    private String frontShiny;

    public Sprites() {
    }

    public Sprites(String front, String frontShiny) {
        this.front = front;
        this.frontShiny = frontShiny;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }
}

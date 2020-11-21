package com.juanbravo.retopokeapps.model;

import java.io.Serializable;

public class StatType implements Serializable {

    private String name;

    public StatType() {
    }

    public StatType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

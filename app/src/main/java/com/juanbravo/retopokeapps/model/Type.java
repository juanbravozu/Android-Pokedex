package com.juanbravo.retopokeapps.model;

import java.io.Serializable;

public class Type implements Serializable {

    private int slot;
    private TypeName type;

    public Type() {
    }

    public Type(int slot, TypeName type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }
}

package me.cadox8.deud.attributes;

import lombok.Getter;

public abstract class Attribute {

    @Getter private int id;
    @Getter private String name;

    public Attribute(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void perform();
}

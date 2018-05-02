package me.cadox8.deud.attributes;

import lombok.Getter;
import me.cadox8.deud.api.API;

public abstract class Attribute {

    protected final API API;
    @Getter private final int id;
    @Getter private final String name;

    public Attribute(API API, int id, String name) {
        this.API = API;
        this.id = id;
        this.name = name;
    }

    public abstract void perform();
}

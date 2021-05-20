package me.cadox8.deud.entities.statics.trees;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.enums.EntityType;
import me.cadox8.deud.entities.statics.StaticEntity;
import me.cadox8.deud.tiles.Tile;

public abstract class Tree extends StaticEntity {

    @Getter @Setter protected int treeType = 0;

    public Tree(String uuid, String name, EntityType type, GameAPI gameAPI, float x, float y) {
        this(uuid, name, type, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }
    public Tree(String uuid, String name, EntityType type, GameAPI gameAPI, float x, float y, int width, int height) {
        super(uuid, name, type, gameAPI, x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
    }
}

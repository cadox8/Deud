package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.states.GameState;
import me.cadox8.deud.states.State;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.ux.dialog.Dialog;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Door extends StaticEntity {

    @Getter private final String map;
    @Getter @Setter private int neededItem = -1;

    public Door(@NonNull GameAPI gameAPI, float x, float y, String map) {
        super(250, "Door", EntityData.EntityType.DOOR, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.map = map;

        setDamageable(false);

        bounds.x = 2;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void changeWorld(Player p) {
        if (!canOpen(p)) {
            ((GameState)getGameAPI().getGame().getGameState()).setDialog(new Dialog(getGameAPI(), p).addText(Arrays.asList("You need " + Item.items[neededItem].getName() + " to open this")));
            return;
        }
        if (!new File("resources/worlds/" + map + "/world.txt").exists()) {
            ((GameState)getGameAPI().getGame().getGameState()).setDialog(new Dialog(getGameAPI(), p).addText(Arrays.asList("You hear sounds inside but the door seems to be locked")));
            return;
        }
        Log.log("Teleporting to " + map);
        FileUtils.save(gameAPI.getWorld().getEntityManager().getPlayer());
        gameAPI.getWorld().getEntityManager().getEntities().forEach(gameAPI.getWorld().getEntityManager()::removeEntity);

        final GameState gameState = new GameState(gameAPI, getMap(), true);

        gameAPI.getGame().setGameState(gameState);
        State.setState(gameState);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.door2, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.door, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
    }


    private boolean canOpen(Player p) {
        if (neededItem == -1) return true;
        return p.getPlayerInventory().hasItem(neededItem);
    }
}

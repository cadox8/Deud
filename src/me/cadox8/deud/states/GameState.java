package me.cadox8.deud.states;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.dialog.Dialog;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;
    @Getter @Setter private Dialog dialog;

    public GameState(API API, String map) {
        this(API, map, false);
    }
    public GameState(API API, String map, boolean change) {
        super(API);
        Location.setAPI(API);
        if (!change) Game.getInstance().setPlayerData(FileUtils.load());
        if (Game.getInstance().getPlayerData() != null && Game.getInstance().getPlayerData().getHealth() <= 0) Game.getInstance().getPlayerData().setHealth(10); // Temporal
        Game.getInstance().setSettings(FileUtils.loadSettings());

        if (!change) {
            try {
                map = Game.getInstance().getPlayerData().locUtils().getWorld();
            } catch (NullPointerException e) {}
        }

        Game.getInstance().setEntityData(FileUtils.loadEntities(map));

        world = new World(API, "resources/worlds/" + map + "/world.txt");

        API.setWorld(world);
        API.getWorld().getEntityManager().getPlayer().loadMiniMap();
    }

    @Override
    public void tick() {
        if (dialog != null) {
            dialog.tick();
            if (dialog.isEnd()) dialog = null;
        }
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        if (dialog != null) dialog.render(g);
    }
}

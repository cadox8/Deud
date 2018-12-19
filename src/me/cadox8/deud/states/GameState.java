package me.cadox8.deud.states;

import me.cadox8.deud.api.API;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.utils.Location;
import me.cadox8.deud.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(API API, String map) {
        super(API);
        Location.setAPI(API);
        Game.getInstance().setPlayerData(FileUtils.load());
        Game.getInstance().setEntityData(FileUtils.loadEntities());
        if (Game.getInstance().getPlayerData() != null && Game.getInstance().getPlayerData().getHealth() <= 0) Game.getInstance().getPlayerData().setHealth(10); // Temporal
        Game.getInstance().setSettings(FileUtils.loadSettings());

        try {
            map = Game.getInstance().getPlayerData().locUtils().getWorld();
        } catch (NullPointerException e) {}

        world = new World(API, "resources/worlds/" + map + ".txt");

        API.setWorld(world);
        API.getWorld().getEntityManager().getPlayer().loadMiniMap();
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}

package me.cadox8.deud.states;

import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.API;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.utils.Location;
import me.cadox8.deud.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(API API, String map) {
        super(API);
        Location.setAPI(API);
        Launcher.getGame().setPlayerData(FileUtils.load());
        if (Launcher.getGame().getPlayerData() != null && Launcher.getGame().getPlayerData().getHealth() <= 0) Launcher.getGame().getPlayerData().setHealth(10); // Temporal
        Launcher.getGame().setSettings(FileUtils.loadSettings());

        try {
            map = Launcher.getGame().getPlayerData().locUtils().getWorld();
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

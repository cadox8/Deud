package me.cadox8.deud.states;

import me.cadox8.deud.api.API;
import me.cadox8.deud.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(API API, String map) {
        super(API);

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

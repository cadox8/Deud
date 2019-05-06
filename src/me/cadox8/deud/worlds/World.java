package me.cadox8.deud.worlds;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.items.ItemManager;
import me.cadox8.deud.particles.Particle;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.tiles.Tiles;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private GameAPI gameAPI;

    @Getter private int width, height;
    private int spawnX, spawnY;
    private TileUtils[][] tiles;

    private String path;

    //Entities
    @Getter private final EntityManager entityManager;
    // Item
    @Getter private final ItemManager itemManager;

    private ArrayList<Particle> particles;

    public World(GameAPI gameAPI, String path) {
        this.gameAPI = gameAPI;
        this.path = path;
        Location loc;

        loadWorld(path);

        try {
            loc = Game.getInstance().getPlayerData().getLocation();
        } catch (NullPointerException e) {
            loc = new Location(spawnX, spawnY, 0);
        }

        this.entityManager = new EntityManager(gameAPI, new Player(gameAPI, loc.getX(), loc.getY()));
        this.itemManager = new ItemManager(gameAPI);

        addEntities();

        particles = new ArrayList<>();
        particles.add(Particle.EXPLOSION);
    }

    private void addEntities() {
        try {
            if (Game.getInstance().getEntityData() == null) {
                final EntityData data = new GsonBuilder().create().fromJson(new JsonReader(new FileReader(new File("resources/worlds/" + worldName() + "/entities.json"))), EntityData.class);
                Game.getInstance().setEntityData(data);
            }
            new WorldEntities(gameAPI, entityManager, worldName());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(5);
        }
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();

        // Particles
        particles.forEach(Particle::tick);
        particles.removeIf(p -> p.getAnimation().hasEnd());
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, gameAPI.getGameCamera().getXOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (gameAPI.getGameCamera().getXOffset() + gameAPI.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, gameAPI.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (gameAPI.getGameCamera().getYOffset() + gameAPI.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                //getTile(x, y).createNewRotated(45).render(g, (int) (x * Tile.TILEWIDTH - gameAPI.getGameCamera().getXOffset()), (int) (y * Tile.TILEHEIGHT - gameAPI.getGameCamera().getYOffset()));
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - gameAPI.getGameCamera().getXOffset()), (int) (y * Tile.TILEHEIGHT - gameAPI.getGameCamera().getYOffset()));
            }
        }

        // Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);

        particles.forEach(p -> p.render(g, 5, 5));
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tiles.VOID.build();
        final TileUtils tu = tiles[x][y];
        return Tiles.getTile(tu.getId(), tu.getSubID());
    }

    private void loadWorld(String path) {
        final String[] tokens = Utils.loadFileAsString(path).split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new TileUtils[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final String token = tokens[(x + y * width) + 4];
                if (token.contains(":")) {
                    final String[] parts = token.split(":");
                    tiles[x][y] = new TileUtils(Utils.parseInt(parts[0]), Utils.parseInt(parts[1]));
                } else {
                    tiles[x][y] = new TileUtils(Utils.parseInt(token), 0);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "World{Name: " + worldName() + ", Entities: " + entityManager.getEntities().toString() + "}";
    }
    public String worldName() {
        return path.split("/")[2].split("\\.")[0];
    }

    public Player getPlayer() {
        return getEntityManager().getPlayer();
    }

    @RequiredArgsConstructor
    private class TileUtils {
        @Getter private final int id;
        @Getter private final int subID;
    }
}

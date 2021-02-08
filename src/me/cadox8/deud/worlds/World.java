package me.cadox8.deud.worlds;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.*;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.managers.EmotesManager;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.managers.ItemManager;
import me.cadox8.deud.managers.ParticleManager;
import me.cadox8.deud.particles.Particle;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.tiles.Tiles;
import me.cadox8.deud.ui.components.base.UIBlock;
import me.cadox8.deud.ui.helpers.NysvaColor;
import me.cadox8.deud.ui.helpers.UIDimension;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class World {

    private final GameAPI gameAPI;

    @Getter private int width, height;
    private int spawnX, spawnY;
    @Getter private TileUtils[][] tiles;

    private final String path;

    private boolean dark;

    //
    private final UIBlock base;
    //

    //Entities
    @Getter private final EntityManager entityManager;

    // Item
    @Getter private final ItemManager itemManager;

    // Particles
    @Getter private final ParticleManager particleManager;

    // Particles
    @Getter private final EmotesManager emotesManager;

    public World(@NonNull GameAPI gameAPI, String path) {
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
        this.particleManager = new ParticleManager(gameAPI);
        this.emotesManager = new EmotesManager(gameAPI);

        addEntities();

        base = new UIBlock(gameAPI, NysvaColor.BLACK);
        base.setUiDimension(new UIDimension(0, 0, gameAPI.getWidth(), gameAPI.getHeight()));
    }

    private void addEntities() {
        try {
            if (Game.getInstance().getEntityData() == null) {
                final EntityData data = new GsonBuilder().create().fromJson(new JsonReader(new FileReader(new File("resources/worlds/" + worldName() + "/entities.ddata"))), EntityData.class);
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
        particleManager.tick();
        emotesManager.tick();
    }

    public void render(Graphics g) {
        base.render(g);

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

        itemManager.render(g);
        entityManager.render(g, dark);
        particleManager.render(g);
        emotesManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tiles.VOID.build();
        final TileUtils tu = tiles[x][y];
        if (tu == null) return Tiles.VOID.build();
        return Tiles.getTile(tu.getId(), tu.getSubID());
    }

    private void loadWorld(String path) {
        final WorldData worldData = new GsonBuilder().setPrettyPrinting().create().fromJson(Utils.loadFileAsString(path), WorldData.class);

        width = worldData.getWidth();
        height = worldData.getHeight();

        dark = worldData.getLight() == 0;

        tiles = new TileUtils[width][height];
        final AtomicInteger x = new AtomicInteger(0);
        final AtomicInteger y = new AtomicInteger(0);

        worldData.getTiles().forEach(t -> {
            if (t.contains(":")) {
                final String[] parts = t.split(":");
                tiles[x.get()][y.get()] = new TileUtils(Utils.parseInt(parts[0]), Utils.parseInt(parts[1]));
            } else {
                tiles[x.get()][y.get()] = new TileUtils(Utils.parseInt(t), 0);
            }
            if (x.incrementAndGet() >= width) {
                x.set(0);
                y.incrementAndGet();
            }
        });
        worldData.getParticles().forEach(p -> getParticleManager().addParticle(p));

        Log.success("World " + worldData.name + " loaded! v" + worldData.version);
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
    public static class TileUtils {
        @Getter private final int id;
        @Getter private final int subID;
    }

    @RequiredArgsConstructor
    @ToString
    private static class WorldData {
        @Getter private final String version;
        @Getter private final String name;
        @Getter private final int width;
        @Getter private final int height;

        @Getter private final float light;

        private final String[] tiles;

        private final ParticleData[] particles;

        public List<String> getTiles() {
            return Arrays.asList(tiles);
        }

        public List<Particle> getParticles() {
            final List<Particle> particle = new ArrayList<>();
            Arrays.asList(particles).forEach(p -> particle.add(Particle.valueOf(p.getName()).setPosition(p.getX(), p.getY())));
            return particle;
        }

        @Data
        @RequiredArgsConstructor
        private static class ParticleData {
            private final String name;
            private final int x, y;
            private final boolean persistent;
            private boolean show;
        }
    }
}

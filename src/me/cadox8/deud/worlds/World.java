package me.cadox8.deud.worlds;

import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.entities.creatures.friends.Fairy;
import me.cadox8.deud.entities.creatures.monsters.Ghost;
import me.cadox8.deud.entities.creatures.monsters.Zombie;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.Chest;
import me.cadox8.deud.entities.statics.Rock;
import me.cadox8.deud.entities.statics.SignEntity;
import me.cadox8.deud.entities.statics.Tree;
import me.cadox8.deud.items.ItemManager;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.util.Arrays;

public class World {

    private API API;

    @Getter private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    private String path;

    //Entities
    @Getter private EntityManager entityManager;
    // Item
    @Getter private ItemManager itemManager;

    public World(API API, String path) {
        this.API = API;
        this.entityManager = new EntityManager(API, new Player(API, 100, 100));
        this.itemManager = new ItemManager(API);

        this.path = path;

        addEntities();
        loadWorld(path);

        this.entityManager.getPlayer().setX(spawnX);
        this.entityManager.getPlayer().setY(spawnY);
    }

    private void addEntities() {
        //Static Entities
        entityManager.addEntity(new Tree(API, 130, 250));
        entityManager.addEntity(new Rock(API, 130, 450));
        entityManager.addEntity(new Tree(API, 130, 650));
        entityManager.addEntity(new Rock(API, 130, 850));

        entityManager.addEntity(new SignEntity(API, 500, 150, 0, Arrays.asList("This is a Test")));
        entityManager.addEntity(new SignEntity(API, 500, 250, 1, Arrays.asList("Hello World", "asdasd", "sadasd")));

        entityManager.addEntity(new Chest(API, 600, 120));

        //Creatures
        entityManager.addEntity(new Fairy(API, 135, 100));
        entityManager.addEntity(new Zombie(API, 200, 100));
        entityManager.addEntity(new Ghost(API, 300, 100));
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, API.getGameCamera().getXOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (API.getGameCamera().getXOffset() + API.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, API.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (API.getGameCamera().getYOffset() + API.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                //Magic
                //getTile(x, y).createNewRotated(45).render(g, (int) (x * Tile.TILEWIDTH - API.getGameCamera().getXOffset()), (int) (y * Tile.TILEHEIGHT - API.getGameCamera().getYOffset()));
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - API.getGameCamera().getXOffset()), (int) (y * Tile.TILEHEIGHT - API.getGameCamera().getYOffset()));
            }
        }

        // Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.bug;
        Tile t = Tile.tiles[tiles[x][y]];

        if (t == null) return Tile.bug;
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    @Override
    public String toString() {
        return "World{Name:" + worldName() + "}";
    }
    public String worldName() {
        return path.split("/")[2].split(".")[0];
    }
}

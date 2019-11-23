package me.cadox8.deud.saves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.*;
import me.cadox8.deud.entities.statics.sign.SignEntity;
import me.cadox8.deud.entities.statics.trees.Tree;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.inventory.PlayerInventory;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.worlds.World;
import net.arikia.dev.drpc.DiscordRPC;

import java.io.*;
import java.util.List;

public class FileUtils {

    private static File saves = new File(Launcher.GAME_FILE + "saves", "player.json");
    private static File config = new File(Launcher.GAME_FILE, "config.json");

    public static void checkFile() {
        final File file = new File(Launcher.GAME_FILE + "saves/entities");
        try {
            if (!saves.exists()) {
                saves.getParentFile().mkdirs();
                saves.createNewFile();
            }
            if (!file.exists()) file.mkdirs();
            if (!config.exists()) {
                config.createNewFile();
            }
        } catch (IOException e) { }
    }


    @SuppressWarnings("Unchecked")
    public static void save(Player p){
        final PlayerInventory i = p.getPlayerInventory();
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Game.setFirst(false);

        try {
            final JsonObject data = new JsonObject();

            final JsonArray inv = new JsonArray();
            i.getItems().forEach(item -> {
                JsonObject it = new JsonObject();
                it.addProperty(item.getId() + "", item.getCount());
                inv.add(it);
            });

            final JsonObject item = new JsonObject();
            item.addProperty("id", i.getUsableItem().getId());
            item.addProperty("count", i.getUsableItem().getCount());

            data.addProperty("nick", p.getNick());
            data.addProperty("health", p.getHealth());
            data.addProperty("money", p.getMoney());
            data.add("inventory", inv);
            data.add("location", gson.toJsonTree(p.getLocation().serializeLocation()).getAsJsonObject());
            data.add("item", item);

            final BufferedWriter w = new BufferedWriter(new FileWriter(saves));

            if (saves.exists()) saves.delete(); saves.mkdirs();

            w.write(gson.toJson(data));
            w.close();

            saveEntities(p.getGameAPI().getWorld());

            Log.log(Log.LogType.SUCCESS, "Data saved successfully");
        } catch (IOException e){
            Log.log(Log.LogType.DANGER, "Error while saving data. Does 'C:/Deud/saves' exist?");
        }

        DiscordRPC.discordShutdown();
    }

    public static PlayerData load() {
        if (!saves.exists()) return null;

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(saves)), PlayerData.class);
        } catch (IOException e) {
            return null;
        }
    }

    @SuppressWarnings("Unchecked")
    public static void saveEntities(World world) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final List<Entity> entities = world.getEntityManager().getEntities();
        final File saveEntities = new File(Launcher.GAME_FILE + "saves/entities", "ent_" + world.worldName() +".json");

        final JsonObject data = new JsonObject();
        final JsonArray ent = new JsonArray();

        entities.stream().filter(e -> !(e instanceof Player)).forEach(e -> {
            final JsonObject en = new JsonObject();

            en.addProperty("type", e.getINTERNAL_NAME());
            en.addProperty("health", e.getHealth());
            en.addProperty("maxHealth", e.getMaxHealth());
            en.add("location", gson.toJsonTree(e.getLocation().serializeLocation()).getAsJsonObject());

            if (e instanceof Creature) {
                if (e.getInventory() != null) {
                    final JsonArray inv = new JsonArray();
                    final JsonArray items = new JsonArray();
                    e.getInventory().getItems().forEach(i -> {
                        final JsonObject item = new JsonObject();
                        item.addProperty("id", i.getId());
                        item.addProperty("count", i.getCount());
                        items.add(item);
                    });
                    en.add("inventory", inv);
                }
            }

            if (e instanceof Tree) en.addProperty("treeType", ((Tree) e).getTreeType());
            if (e instanceof Chest) {
                final JsonArray items = new JsonArray();
                e.getInventory().getItems().forEach(i -> {
                    final JsonObject item = new JsonObject();
                    item.addProperty("id", i.getId());
                    item.addProperty("count", i.getCount());
                    items.add(item);
                });
                en.add("inventory", items);
            }
            if (e instanceof RewardChest) {
                en.addProperty("open", ((RewardChest)e).isOpen());
            }
            if (e instanceof SignEntity) {
                final JsonArray text = new JsonArray();
                ((SignEntity) e).getText().forEach(text::add);
                en.addProperty("signType", ((SignEntity) e).getType());
                en.add("text", text);
            }
            if (e instanceof Door) {
                en.addProperty("map", ((Door) e).getMap());
                en.addProperty("neededItem", -1);
            }
            if (e instanceof Shop) {
                final JsonArray items = new JsonArray();
                e.getInventory().getItems().forEach(i -> {
                    final JsonObject item = new JsonObject();
                    item.addProperty("id", i.getId());
                    item.addProperty("count", i.getCount());
                    items.add(item);
                });
                en.add("inventory", items);
            }
            if (e instanceof Npc) {
                final JsonArray text = new JsonArray();
                final JsonArray items = new JsonArray();
                ((Npc)e).getText().forEach(text::add);
                e.getInventory().getItems().forEach(i -> {
                    final JsonObject item = new JsonObject();
                    item.addProperty("id", i.getId());
                    item.addProperty("count", i.getCount());
                    items.add(item);
                });
                en.addProperty("displayName", ((Npc)e).getDisplayName());
                en.add("text", text);
                en.add("inventory", items);
            }
            if (e instanceof House) {
                en.addProperty("houseType", ((House)e).getHouseType());
            }
            ent.add(en);
        });

        data.add("entities", ent);

        final BufferedWriter w = new BufferedWriter(new FileWriter(saveEntities));
        if (saveEntities.exists()) saveEntities.delete(); saveEntities.mkdirs();
        w.write(gson.toJson(data));
        w.close();
        Log.log(Log.LogType.SUCCESS, "Entity Data saved successfully");
    }

    public static EntityData loadEntities(String world) {
        final File saveEntities = new File(Launcher.GAME_FILE + "saves/entities", "ent_" + world +".json");
        if (!saveEntities.exists()) return null;

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(saveEntities)), EntityData.class);
        } catch (IOException e) {
            return null;
        }
    }
}
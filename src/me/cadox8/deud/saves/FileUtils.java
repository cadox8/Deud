package me.cadox8.deud.saves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.SignEntity;
import me.cadox8.deud.inventory.Inventory;
import me.cadox8.deud.settings.Settings;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.worlds.World;
import net.arikia.dev.drpc.DiscordRPC;

import java.io.*;
import java.util.List;

public class FileUtils {

    private static File saves = new File(Launcher.GAME_FILE + "saves", "save.json");
    private static File saveEntities = new File(Launcher.GAME_FILE + "saves", "entities.json");
    private static File config = new File(Launcher.GAME_FILE, "config.json");

    public static void checkFile() {
        try {
            if (!saves.exists()) {
                saves.getParentFile().mkdirs();
                saves.createNewFile();
            }
            if (!saveEntities.exists()) {
                saveEntities.getParentFile().mkdirs();
                saveEntities.createNewFile();
            }
            if (!config.exists()) {
                config.createNewFile();
                saveSettings(new Settings());
            }
        } catch (IOException e) { }
    }

    public static void saveSettings(Settings s) {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            final JsonObject settings = new JsonObject();

            settings.addProperty("volume", s.getVolume());
            settings.addProperty("windows", s.getWindows());
            settings.addProperty("mode", s.getMode());

            final BufferedWriter w = new BufferedWriter(new FileWriter(config));
            if (config.exists()) config.delete(); config.createNewFile();

            w.write(gson.toJson(settings));
            w.close();
            Log.log(Log.LogType.SUCCESS, "Config saved successfully");
        } catch (IOException e) {
            Log.log(Log.LogType.DANGER, "Error while saving settings. Does 'C:/Deud/config.json' exist?");
        }
    }

    public static Settings loadSettings() {
        if (!config.exists()) return new Settings();

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(config)), Settings.class);
        } catch (IOException e) {
            return new Settings();
        }
    }



    @SuppressWarnings("Unchecked")
    public static void save(Player p){
        final Inventory i = p.getInventory();
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            final JsonObject data = new JsonObject();

            final JsonArray inv = new JsonArray();
            i.getInventoryItems().forEach(item -> {
                JsonObject it = new JsonObject();
                it.addProperty(item.getId() + "", item.getCount());
                inv.add(it);
            });

            data.addProperty("Health", p.getHealth());
            data.addProperty("Money", p.getMoney());
            data.add("Inventory", inv);
            data.add("Location", gson.toJsonTree(p.getLocation().serializeLocation()).getAsJsonObject());

            final BufferedWriter w = new BufferedWriter(new FileWriter(saves));

            if (saves.exists()) saves.delete(); saves.mkdirs();

            w.write(gson.toJson(data));
            w.close();

            saveEntities(p.getAPI().getWorld());

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

        final JsonObject data = new JsonObject();
        final JsonArray ent = new JsonArray();

        entities.stream().filter(e -> !(e instanceof Player)).forEach(e -> {
            final JsonObject en = new JsonObject();
            en.addProperty("type", e.getINTERNAL_NAME());
            en.add("location", gson.toJsonTree(e.getLocation().serializeLocation()).getAsJsonObject());
            if (e instanceof SignEntity) {
                final JsonArray text = new JsonArray();
                ((SignEntity) e).getWhatToSay().forEach(text::add);
                en.addProperty("signType", ((SignEntity) e).getType());
                en.add("text", text);
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

    public static EntityData loadEntities() {
        if (!saveEntities.exists()) return null;

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(saveEntities)), EntityData.class);
        } catch (IOException e) {
            return null;
        }
    }
}

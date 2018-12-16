package me.cadox8.deud.saves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.inventory.Inventory;
import me.cadox8.deud.settings.Settings;
import me.cadox8.deud.utils.Location;
import me.cadox8.deud.utils.Log;

import java.io.*;

public class FileUtils {

    private static File saves = new File(Launcher.GAME_FILE + "saves", "save.json");
    private static File config = new File(Launcher.GAME_FILE, "config.json");

    public static void checkFile() {
        try {
            if (!saves.exists()) {
                saves.getParentFile().mkdirs();
                saves.createNewFile();
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
            JsonObject settings = new JsonObject();

            settings.addProperty("volume", s.getVolume());
            settings.addProperty("windows", s.getWindows());
            settings.addProperty("mode", s.getMode());

            BufferedWriter w = new BufferedWriter(new FileWriter(config));
            if (config.exists()) config.delete(); config.createNewFile();

            w.write(gson.toJson(settings));
            w.close();
            Log.log(Log.LogType.SUCCESS, "Config saved successfully");
        } catch (IOException e) {
            Log.log(Log.LogType.DANGER, "Error while saving settings. Does 'C:/Deud/config.json' exist?");
        }
    }

    public static Settings loadSettings() {
        if (!config.exists()) return null;

        try {
            JsonReader reader = new JsonReader(new FileReader(config));
            Gson g = new GsonBuilder().create();

            return g.fromJson(reader, Settings.class);
        } catch (IOException e) {
            return new Settings();
        }
    }



    @SuppressWarnings("Unchecked")
    public static void save(Player p){
        final Location l = p.getLocation();
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

            Log.log(Log.LogType.SUCCESS, "Data saved successfully");
        } catch (IOException e){
            Log.log(Log.LogType.DANGER, "Error while saving data. Does 'C:/Deud/saves' exist?");
        }
    }

    public static PlayerData load() {
        if (!saves.exists()) return null;

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(saves)), PlayerData.class);
        } catch (IOException e) {
            return null;
        }
    }
}

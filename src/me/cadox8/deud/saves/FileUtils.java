package me.cadox8.deud.saves;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.config.Config;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.PlayerData;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.utils.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    private static final File saves = new File(Launcher.GAME_FILE + "saves", "player.ddata");
    private static final File config = new File(Launcher.GAME_FILE, "config.json");

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
                new Save(config).saveConfig(new Config());
            }
        } catch (IOException e) { }
    }

    public static void save(Player player, GameAPI gameAPI) {
        final Save playerSave = new Save(saves);
        final Save entitySave = new Save(null);
        final Save configSave = new Save(config);

        try {
            Log.system("-----------------");
            playerSave.savePlayer(player);
            entitySave.saveEntities(gameAPI.getEntityManager().getEntities(), gameAPI.getWorld().worldName());
            configSave.saveConfig(gameAPI.getConfig());
            Log.system("-----------------");
        } catch (IOException e) {
            Log.danger("Could not save a file!");
            Log.danger(e.getMessage());
        }
    }

    // Config
    public static Config loadConfig() {
        if (!config.exists()) {
            checkFile();
            return loadConfig();
        }

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(config)), Config.class);
        } catch (IOException e) {
            return new Config();
        }
    }

    // Player Data
    public static PlayerData load() {
        if (!saves.exists()) {
            checkFile();
            return load();
        }

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(saves)), PlayerData.class);
        } catch (IOException e) {
            return null;
        }
    }

    // Entity Data
    public static EntityData loadEntities(String world) {
        final File saveEntities = new File(Launcher.GAME_FILE + "saves/entities", "ent_" + world +".ddata");
        if (!saveEntities.exists()) return null;

        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(saveEntities)), EntityData.class);
        } catch (IOException e) {
            return null;
        }
    }
}
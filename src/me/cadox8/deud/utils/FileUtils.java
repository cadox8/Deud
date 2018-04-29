package me.cadox8.deud.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.inventory.Inventory;

import java.io.*;

public class FileUtils {

    private static File f = new File(Launcher.GAME_FILE + "saves", "save.json");

    @SuppressWarnings("Unchecked")
    public static void save(Player p){
        Location l = p.getLocation();
        Inventory i = p.getInventory();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            JsonObject data = new JsonObject();

            JsonArray loc = new JsonArray();
            loc.add(l.getX());
            loc.add(l.getY());
            loc.add(l.getDirection());

            JsonArray inv = new JsonArray();
            i.getInventoryItems().forEach(item -> {
                JsonObject it = new JsonObject();
                it.addProperty(item.getId() + "", item.getCount());
                inv.add(it);
            });

            data.addProperty("Health", p.getHealth());
            data.addProperty("Money", p.getMoney());
            data.add("Inventory", inv);
            data.add("Location", loc);

            BufferedWriter w = new BufferedWriter(new FileWriter(f));

            if (f.exists()) f.delete(); f.mkdirs();

            w.write(gson.toJson(data));
            w.close();

            Log.log(Log.LogType.SUCCESS, "Data saved successfully");
        } catch (IOException e){
            Log.log(Log.LogType.DANGER, "Error while saving data. Does 'C:/Deud/saves' exist?");
        }
    }

    public static void load() {
        if (!f.exists()) return;

        try {
            BufferedReader r = new BufferedReader(new FileReader(f));


        } catch (IOException e) {

        }
    }
}

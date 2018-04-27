package me.cadox8.deud.utils;

import me.cadox8.deud.Launcher;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.inventory.Inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    //ToDo: Change to Gson

    private static File f = new File(Launcher.GAME_FILE + "saves", "save.json");

    @SuppressWarnings("Unchecked")
    public static void save(Player p){
        Location l = p.getLocation();
        Inventory i = p.getInventory();
        /*try {
            JSONObject data = new JSONObject();

            JSONArray loc = new JSONArray();
            loc.add(l.getX());
            loc.add(l.getY());
            loc.add(l.getDirection());

            JSONArray inv = new JSONArray();
            for (Item it : i.getInventoryItems()) inv.add(it.getId());

            data.put("Health", p.getHealth());
            data.put("Money", p.getMoney());
            data.put("Inventory", inv);
            data.put("Location", loc);

            BufferedWriter w = new BufferedWriter(new FileWriter(f));

            if (f.exists()) f.delete();
            f.mkdirs();

            w.write(data.toJSONString());
            w.close();

            Log.log(Log.LogType.SUCCESS, "Data saved successfully");
        } catch (IOException e){
            Log.log(Log.LogType.DANGER, "Error while saving data. Does 'C:/Deud/saves' exist?");
        }*/
    }

    public static void load() {
        if (!f.exists()) return;

        try {
            BufferedReader r = new BufferedReader(new FileReader(f));


        } catch (IOException e) {

        }
    }
}

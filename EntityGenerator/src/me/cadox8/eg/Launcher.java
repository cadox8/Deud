package me.cadox8.eg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.statics.Door;
import me.cadox8.deud.entities.statics.Shop;
import me.cadox8.deud.entities.statics.sign.SignEntity;
import me.cadox8.deud.items.Item;

import java.util.Arrays;

public class Launcher {

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        final Npc e = new Npc(null, 500, 100, "Pepe");
        e.addTexts("hola", "Buenas");
        e.addItems(Item.getRandom(), Item.getRandom());

        System.out.println(e);
    }


    private static String getJSON(Entity e) {
        final JsonObject en = new JsonObject();
        en.addProperty("type", e.getINTERNAL_NAME());
        en.addProperty("health", e.getHealth());
        en.addProperty("maxHealth", e.getMaxHealth());
        en.add("location", gson.toJsonTree(e.getLocation().serializeLocation()).getAsJsonObject());
        if (e instanceof SignEntity) {
            final JsonArray text = new JsonArray();
            ((SignEntity) e).getText().forEach(text::add);
            en.addProperty("signType", ((SignEntity) e).getType());
            en.add("text", text);
        }
        if (e instanceof Door) en.addProperty("map", ((Door) e).getMap());
        if (e instanceof Shop) {
            final JsonArray items = new JsonArray();
            Arrays.asList(((Shop) e).getDrops()).forEach(i -> {
                final JsonObject item = new JsonObject();
                item.addProperty("id", i.getId());
                item.addProperty("count", i.getCount());
                items.add(item);
            });
            en.add("items", items);
        }
        if (e instanceof Npc) {
            final JsonArray text = new JsonArray();
            final JsonArray items = new JsonArray();
            ((Npc)e).getText().forEach(text::add);
            ((Npc)e).getItems().forEach(i -> {
                final JsonObject item = new JsonObject();
                item.addProperty("id", i.getId());
                item.addProperty("count", i.getCount());
                items.add(item);
            });
            en.addProperty("displayName", ((Npc)e).getDisplayName());
            en.add("text", text);
            en.add("items", items);
        }
        return en.getAsString();
    }
}

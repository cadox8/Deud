package me.cadox8.deud.saves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.config.Config;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.Door;
import me.cadox8.deud.entities.statics.House;
import me.cadox8.deud.entities.statics.Light;
import me.cadox8.deud.entities.statics.chest.RewardChest;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.entities.statics.trees.Tree;
import me.cadox8.deud.inventory.creature.PlayerInventory2;
import me.cadox8.deud.utils.Log;
import net.arikia.dev.drpc.DiscordRPC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class Save {

    private final File file;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void savePlayer(Player player) throws IOException {
        final PlayerInventory2 i = player.getPlayerInventory();
        final JsonObject data = new JsonObject();

        data.addProperty("nick", player.getNick());
        data.addProperty("health", player.getHealth());
        data.addProperty("money", player.getMoney());
        data.addProperty("level", player.getLevel());
        data.addProperty("experience", player.getXp());

        final JsonArray inv = new JsonArray();
        i.getItems().forEach(item -> {
            final JsonObject it = new JsonObject();
            it.addProperty("id", item.getId());
            it.addProperty("count", item.getCount());
            inv.add(it);
        });
        data.add("inventory", inv);

        data.add("location", gson.toJsonTree(player.getLocation().serializeLocation()).getAsJsonObject());

        final JsonObject item = new JsonObject();
        item.addProperty("id", i.getUsableItem().getId());
        item.addProperty("count", i.getUsableItem().getCount());
        data.add("item", item);

        final BufferedWriter w = new BufferedWriter(new FileWriter(file));

        if (file.exists()) file.delete(); file.mkdirs();

        w.write(gson.toJson(data));
        w.close();

        Log.success("Data saved successfully");

        DiscordRPC.discordShutdown();
    }

    public void saveEntities(List<Entity> entities, String worldName) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final File saveEntities = new File(Launcher.GAME_FILE + "saves/entities", "ent_" + worldName +".ddata");

        final JsonObject data = new JsonObject();
        final JsonArray ent = new JsonArray();

        entities.stream().filter(e -> !(e instanceof Player)).forEach(e -> {
            final JsonObject en = new JsonObject();

            en.addProperty("type", e.getINTERNAL_NAME());
            en.addProperty("health", e.getHealth());
            en.addProperty("maxHealth", e.getMaxHealth());
            en.add("location", gson.toJsonTree(e.getLocation().serializeLocation()).getAsJsonObject());

            en.addProperty("level", e.getLevel());
            en.addProperty("experience", e.getXp());

            if (e.getInventory() != null) {
                final JsonArray items = new JsonArray();
                e.getInventory().getItems().forEach(i -> {
                    final JsonObject item = new JsonObject();
                    item.addProperty("id", i.getId());
                    item.addProperty("count", i.getCount());
                    items.add(item);
                });
                en.add("inventory", items);
            }

            if (e instanceof Tree) en.addProperty("treeType", ((Tree) e).getTreeType());

            if (e instanceof RewardChest) {
                en.addProperty("open", ((RewardChest)e).isOpen());
            }

            if (e instanceof Sign) {
                final JsonArray text = new JsonArray();
                ((Sign) e).getText().forEach(text::add);
                en.add("text", text);
            }

            if (e instanceof Door) {
                en.addProperty("map", ((Door) e).getMap());
                en.addProperty("neededItem", -1);
            }


            if (e instanceof Npc) {
                final JsonArray text = new JsonArray();
                ((Npc)e).getText().forEach(text::add);
                en.addProperty("displayName", ((Npc)e).getDisplayName());
                en.add("text", text);
            }

            if (e instanceof House) {
                en.addProperty("houseType", ((House)e).getHouseType());
            }

            if (e instanceof Light) {
                if (!((Light)e).isStatic()) return;
                en.addProperty("luminosity", ((Light)e).getLuminosity());
                en.addProperty("radius", ((Light)e).getRadius());
            }

            ent.add(en);
        });

        data.add("entities", ent);

        final BufferedWriter w = new BufferedWriter(new FileWriter(saveEntities));
        if (saveEntities.exists()) saveEntities.delete(); saveEntities.mkdirs();
        w.write(gson.toJson(data));
        w.close();

        Log.success("Entity Data saved successfully");
    }

    public void saveConfig(Config cfn) throws IOException {
        final BufferedWriter w = new BufferedWriter(new FileWriter(file));
        final JsonObject data = new JsonObject();

/*        data.addProperty("fullScreen", cfn.isFullScreen());
        data.addProperty("masterVolume", cfn.getMasterVolume());
        data.addProperty("musicVolume", cfn.getMusicVolume());
        data.addProperty("entitiesVolume", cfn.getEntitiesVolume());*/

        if (file.exists()) file.delete(); file.mkdirs();

        w.write(gson.toJson(cfn));
        w.close();

        Log.success("Config saved successfully");
    }
}

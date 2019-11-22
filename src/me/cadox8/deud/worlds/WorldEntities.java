package me.cadox8.deud.worlds;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.npcs.NPC;
import me.cadox8.deud.entities.statics.*;
import me.cadox8.deud.entities.statics.sign.SignEntity;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.gfx.textures.Models;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.utils.Log;

import java.lang.reflect.InvocationTargetException;

public class WorldEntities {

    private final GameAPI gameAPI;
    private final EntityManager entityManager;
    private final String world;

    public WorldEntities(@NonNull GameAPI gameAPI, EntityManager entityManager, String world) {
        this.gameAPI = gameAPI;
        this.entityManager = entityManager;
        this.world = world;

        load();
    }

    private void load() {
        Game.getInstance().getEntityData().getEntities().forEach(e -> {
            try {
                final Location l = e.getLocation();
                final EntityData.EntityType type = EntityData.EntityType.parseClass(e.getType());
                if (type == null) return;
                final Entity en;

                switch (type) {
                    case CHEST:
                        en = new Chest(gameAPI, l.getX(), l.getY());
                        ((Chest)en).addItems(e.getItems());
                        break;
                    case REWARDCHEST:
                        en = new RewardChest(gameAPI, l.getX(), l.getY());
                        ((RewardChest)en).setOpen(e.isOpen());
                        ((RewardChest)en).addItem(e.getItems()[0]);
                        break;
                    case SIGN:
                        en = new SignEntity(gameAPI, l.getX(), l.getY(), e.getSignType(), e.getText());
                        break;
                    case DOOR:
                        en = new Door(gameAPI, l.getX(), l.getY(), e.getMap());
                        ((Door)en).setNeededItem(e.getNeededItem());
                        break;
                    case SHOP:
                        en = new Shop(gameAPI, l.getX(), l.getY(), e.getItems());
                        break;
                    case NPC:
                        en = new NPC(gameAPI, l.getX(), l.getY(), e.getDisplayName(), Models.npc_down, Models.npc_up, Models.npc_left, Models.npc_right);
                        ((NPC) en).addTexts(e.getTextArray());
                        ((NPC) en).addItems(e.getItems());
                        break;
                    case HOUSE:
                        en = new House(gameAPI, l.getX(), l.getY(), e.getHouseType());
                        break;

                    default:
                        en = (Entity) type.getSupClass().getConstructors()[0].newInstance(gameAPI, l.getX(), l.getY());
                        break;
                }
                en.setMaxHealth(e.getMaxHealth());
                en.setHealth(e.getHealth());
                entityManager.addEntity(en);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException er) {
                Log.log(Log.LogType.DANGER, "Error while loading entities. " + er.getMessage());
                er.printStackTrace();
                System.exit(5);
            }
        });
    }
}

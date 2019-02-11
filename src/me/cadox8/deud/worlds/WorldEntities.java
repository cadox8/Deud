package me.cadox8.deud.worlds;

import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.statics.Door;
import me.cadox8.deud.entities.statics.Shop;
import me.cadox8.deud.entities.statics.SignEntity;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.gfx.textures.Models;
import me.cadox8.deud.saves.EntityData;
import me.cadox8.deud.utils.Log;

import java.lang.reflect.InvocationTargetException;

public class WorldEntities {

    private final API API;
    private final EntityManager entityManager;
    private final String world;

    public WorldEntities(API API, EntityManager entityManager, String world) {
        this.API = API;
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
                    case SIGN:
                        en = new SignEntity(API, l.getX(), l.getY(), e.getSignType(), e.getText());
                        break;
                    case DOOR:
                        en = new Door(API, l.getX(), l.getY(), e.getMap());
                        break;
                    case SHOP:
                        en = new Shop(API, l.getX(), l.getY(), e.getItems());
                        break;
                    case NPC:
                        en = new Npc(API, l.getX(), l.getY(), e.getDisplayName(), Models.npc_down, Models.npc_up, Models.npc_left, Models.npc_right);
                        ((Npc) en).addTexts(e.getTextArray());
                        ((Npc) en).addItems(e.getItems());
                        break;

                    default:
                        en = (Entity) type.getSupClass().getConstructors()[0].newInstance(API, l.getX(), l.getY());
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

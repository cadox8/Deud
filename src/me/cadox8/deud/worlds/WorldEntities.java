package me.cadox8.deud.worlds;

import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.saves.EntityData;

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

                switch (type) {
                    case SIGN:
                        entityManager.addEntity((Entity) type.getSupClass().getConstructors()[0].newInstance(API, l.getX(), l.getY(), e.getSignType(), e.getText()));
                        break;
                    case DOOR:
                        entityManager.addEntity((Entity) type.getSupClass().getConstructors()[0].newInstance(API, l.getX(), l.getY(), e.getMap()));
                        break;
                    case SHOP:
                        entityManager.addEntity((Entity) type.getSupClass().getConstructors()[0].newInstance(API, l.getX(), l.getY(), Item.items[e.getItemID()]));
                        break;

                    default:
                        entityManager.addEntity((Entity) type.getSupClass().getConstructors()[0].newInstance(API, l.getX(), l.getY()));
                        break;
                }
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException er) {
                er.printStackTrace();
            }
        });
    }
}

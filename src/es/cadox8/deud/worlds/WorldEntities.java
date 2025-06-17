package es.cadox8.deud.worlds;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.Location;
import es.cadox8.deud.entities.components.inventory.statics.ChestInventory;
import es.cadox8.deud.entities.components.inventory.statics.ShopInventory;
import es.cadox8.deud.entities.creatures.npcs.Npc;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.entities.statics.chest.Chest;
import es.cadox8.deud.entities.statics.chest.RewardChest;
import es.cadox8.deud.entities.statics.chest.TrapChest;
import es.cadox8.deud.entities.statics.door.Door;
import es.cadox8.deud.entities.statics.house.House;
import es.cadox8.deud.entities.statics.light.Light;
import es.cadox8.deud.entities.statics.shop.Shop;
import es.cadox8.deud.entities.statics.sign.Sign;
import es.cadox8.deud.entities.statics.trees.DeadTree;
import es.cadox8.deud.entities.statics.trees.NormalTree;
import es.cadox8.deud.game.Game;
import es.cadox8.deud.graphics.textures.Models;
import es.cadox8.deud.managers.EntityManager;
import es.cadox8.deud.utils.Log;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class WorldEntities {

    private final GameAPI gameAPI;
    private final EntityManager entityManager;

    public WorldEntities(@NonNull GameAPI gameAPI, EntityManager entityManager) {
        this.gameAPI = gameAPI;
        this.entityManager = entityManager;

        load();
    }

    private void load() {
        Game.getInstance().getEntityData().getEntities().forEach(e -> {
            try {
                final Location l = e.getLocation();
                final EntityType type = EntityType.parseClass(e.getType());
                if (type == null) return;
                final Entity en;

                if (type == EntityType.PLAYER) return;

                switch (type) {
                    case CHEST -> {
                        switch (Chest.ChestType.valueOf(e.getChestType())) {
                            case REWARD -> {
                                en = new RewardChest(gameAPI, l.getX(), l.getY(), e.isNeedKey(), Chest.ChestType.valueOf(e.getChestType()));
                                ((RewardChest) en).setOpen(e.isOpen());
                                Arrays.asList(e.getPool()).forEach(i -> ((RewardChest) en).addToPool(i.getId()));
                            }
                            case TRAP -> {
                                en = new TrapChest(gameAPI, l.getX(), l.getY(), e.isNeedKey());
                                ((TrapChest) en).setOpen(e.isOpen());
                                Arrays.asList(e.getPool()).forEach(i -> ((RewardChest) en).addToPool(i.getId()));
                            }
                            default -> {
                                en = new Chest(gameAPI, l.getX(), l.getY(), Chest.ChestType.valueOf(e.getChestType()));
                                en.getInventory().add(e.getInventory());
                            }
                        }
                        if (e.getInventory() != null) {
                            final ChestInventory inv = new ChestInventory(20);
                            inv.add(e.getInventory());
                            en.setInventory(inv);
                        }
                    }
                    case SIGN -> en = new Sign(gameAPI, l.getX(), l.getY(), e.getText());
                    case DOOR -> {
                        en = new Door(gameAPI, l.getX(), l.getY(), e.getMap());
                        ((Door) en).setNeededItem(e.getNeededItem());
                    }
                    case SHOP -> {
                        en = new Shop(gameAPI, l.getX(), l.getY(), e.getInventory());
                        if (e.getInventory() != null) {
                            final ShopInventory inv = new ShopInventory();
                            inv.add(e.getInventory());
                            en.setInventory(inv);
                        }
                    }
                    case NPC -> {
                        en = new Npc(gameAPI, l.getX(), l.getY(), e.getDisplayName(), Models.npc_down, Models.npc_up, Models.npc_left, Models.npc_right);
                        ((Npc) en).addTexts(e.getTextArray());
                    }
                    case HOUSE -> en = new House(gameAPI, l.getX(), l.getY(), e.getHouseType());
                    case NORMALTREE -> {
                        en = new NormalTree(gameAPI, l.getX(), l.getY());
                        ((NormalTree) en).setTreeType(e.getTreeType());
                    }
                    case DEADTREE -> {
                        en = new DeadTree(gameAPI, l.getX(), l.getY());
                        ((DeadTree) en).setTreeType(e.getTreeType());
                    }
                    case LIGHT -> en = new Light(gameAPI, (int) l.getX(), (int) l.getY(), e.getRadius(), e.getLuminosity());
                    default -> en = (Entity) type.getSupClass().getConstructors()[0].newInstance(gameAPI, l.getX(), l.getY());
                }
                en.setMaxHealth(e.getMaxHealth());
                en.setHealth(e.getHealth());

                entityManager.addEntity(en);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException er) {
                Log.danger("Error while loading entities. " + er.getMessage());
                er.printStackTrace();
                System.exit(5);
            }
        });
    }
}

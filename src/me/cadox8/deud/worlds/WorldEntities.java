package me.cadox8.deud.worlds;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.statics.*;
import me.cadox8.deud.entities.statics.chest.Chest;
import me.cadox8.deud.entities.statics.chest.RewardChest;
import me.cadox8.deud.entities.statics.chest.TrapChest;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.entities.statics.trees.DeadTree;
import me.cadox8.deud.entities.statics.trees.NormalTree;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.graphics.textures.Models;
import me.cadox8.deud.inventory.statics.StaticInventory;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.utils.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;

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

                if (type == EntityData.EntityType.PLAYER) return;

                switch (type) {
                    case CHEST:
                        switch (Chest.ChestType.valueOf(e.getChestType())) {
                            case REWARD: {
                                en = new RewardChest(gameAPI, l.getX(), l.getY(), e.isNeedKey(), Chest.ChestType.valueOf(e.getChestType()));
                                ((RewardChest) en).setOpen(e.isOpen());
                                Arrays.asList(e.getPool()).forEach(i -> ((RewardChest) en).addToPool(i.getId()));
                                break;
                            }
                            case TRAP: {
                                en = new TrapChest(gameAPI, l.getX(), l.getY(), e.isNeedKey());
                                ((TrapChest) en).setOpen(e.isOpen());
                                Arrays.asList(e.getPool()).forEach(i -> ((RewardChest) en).addToPool(i.getId()));
                                break;
                            }
                            default: {
                                en = new Chest(gameAPI, l.getX(), l.getY(), Chest.ChestType.valueOf(e.getChestType()));
                                en.getInventory().addItems(e.getInventory());
                                break;
                            }
                        }
                        break;
                    case SIGN:
                        en = new Sign(gameAPI, l.getX(), l.getY(), e.getText());
                        break;
                    case DOOR:
                        en = new Door(gameAPI, l.getX(), l.getY(), e.getMap());
                        ((Door)en).setNeededItem(e.getNeededItem());
                        break;
                    case SHOP:
                        en = new Shop(gameAPI, l.getX(), l.getY(), e.getInventory());
                        break;
                    case NPC:
                        en = new Npc(gameAPI, l.getX(), l.getY(), e.getDisplayName(), Models.npc_down, Models.npc_up, Models.npc_left, Models.npc_right);
                        ((Npc) en).addTexts(e.getTextArray());
                        break;
                    case HOUSE:
                        en = new House(gameAPI, l.getX(), l.getY(), e.getHouseType());
                        break;
                    case NORMALTREE:
                        en = new NormalTree(gameAPI, l.getX(), l.getY());
                        ((NormalTree)en).setTreeType(e.getTreeType());
                        break;
                    case DEADTREE:
                        en = new DeadTree(gameAPI, l.getX(), l.getY());
                        ((DeadTree)en).setTreeType(e.getTreeType());
                        break;
                    case LIGHT:
                        en = new Light(gameAPI, (int)l.getX(), (int)l.getY(), e.getRadius(), e.getLuminosity());
                        break;

                    default:
                        en = (Entity) type.getSupClass().getConstructors()[0].newInstance(gameAPI, l.getX(), l.getY());
                        break;
                }
                en.setMaxHealth(e.getMaxHealth());
                en.setHealth(e.getHealth());

                en.setLevel(e.getLevel());
                en.setXP(e.getExperience());

                if (e.getInventory() != null) {
                    final StaticInventory inv = new StaticInventory(gameAPI);
                    inv.addItems(e.getInventory());
                    en.setInventory(inv);
                }

                entityManager.addEntity(en);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException er) {
                Log.danger("Error while loading entities. " + er.getMessage());
                er.printStackTrace();
                System.exit(5);
            }
        });
    }
}

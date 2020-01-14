package me.cadox8.deud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.entities.creatures.friends.Fairy;
import me.cadox8.deud.entities.creatures.monsters.Ghost;
import me.cadox8.deud.entities.creatures.monsters.Zombie;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.projectile.Arrow;
import me.cadox8.deud.entities.statics.*;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.entities.statics.trees.DeadTree;
import me.cadox8.deud.entities.statics.trees.NormalTree;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.saves.PlayerData;

import java.util.Arrays;
import java.util.List;

@Data
public class EntityData {

    @Getter @Setter private static String world;

    private Entities[] entities;

    public List<Entities> getEntities() {
        return Arrays.asList(entities);
    }

    @Data
    public class Entities {

        private String type = "";
        private int health = 0;
        private int maxHealth = 0;
        private PlayerData.LocationUtils location = null;

        private int treeType = 0;
        private int houseType = 1;
        private String map = "";
        private int neededItem = -1;

        private String[] text = new String[0];
        private ItemHelper[] inventory = new ItemHelper[0];

        private String displayName = "";

        public String[] getTextArray() {
            return text;
        }
        public List<String> getText() { return Arrays.asList(text); }
        public Location getLocation() {
            return new Location(location.getX(), location.getY(),location.getDirection());
        }

        public boolean open = false;

        public Item[] getInventory() {
            if (inventory == null) return new Item[0];
            final Item[] it = new Item[inventory.length];
            for (int x = 0; x < it.length; x++) it[x] = Item.items[inventory[x].getId()].setCount(inventory[x].getCount());
            return it;
        }

        @Data
        public class ItemHelper {

            private int id = 0;
            private int count = 0;
            private double buy = 0;
            private double sell = 0;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum EntityType {

        PLAYER(Player.class),
        ZOMBIE(Zombie.class),
        GHOST(Ghost.class),
        FAIRY(Fairy.class),
        CHEST(Chest.class),
        REWARDCHEST(RewardChest.class),
        ROCK(Rock.class),
        SIGN(Sign.class),
        NORMALTREE(NormalTree.class),
        DEADTREE(DeadTree.class),
        DOOR(Door.class),
        SHOP(Shop.class),
        NPC(Npc.class),
        ARROW(Arrow.class),
        HOUSE(House.class);

        private Class<? extends Entity> supClass;

        public static EntityType parseClass(String name) {
            return Arrays.asList(EntityType.values()).stream().filter(e -> e.name().toLowerCase().equalsIgnoreCase(name.toLowerCase())).findFirst().orElse(null);
        }
    }
}

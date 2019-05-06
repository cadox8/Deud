package me.cadox8.deud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.entities.creatures.friends.Fairy;
import me.cadox8.deud.entities.creatures.monsters.Ghost;
import me.cadox8.deud.entities.creatures.monsters.Zombie;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.projectiles.Arrow;
import me.cadox8.deud.entities.statics.*;
import me.cadox8.deud.entities.statics.sign.SignEntity;
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

        private int signType = 0;

        private String map = "";
        private int neededItem = -1;

        private String[] text = new String[0];
        private ItemHelper[] items = new ItemHelper[0];

        private String displayName = "";


        public String[] getTextArray() {
            return text;
        }
        public List<String> getText() { return Arrays.asList(text); }
        public Location getLocation() {
            return new Location(location.getX(), location.getY(),location.getDirection());
        }

        public Item[] getItems() {
            final Item[] it = new Item[items.length];
            for (int x = 0; x < it.length; x++) it[x] = Item.items[items[x].getId()].setCount(items[x].getCount());
            return it;
        }

        @Data
        public class ItemHelper {

            private int id = 0;
            private int count = 0;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum EntityType {

        ZOMBIE(Zombie.class),
        GHOST(Ghost.class),
        FAIRY(Fairy.class),
        CHEST(Chest.class),
        ROCK(Rock.class),
        SIGN(SignEntity.class),
        TREE(Tree.class),
        DOOR(Door.class),
        SHOP(Shop.class),
        NPC(Npc.class),
        ARROW(Arrow.class);

        private Class<? extends Entity> supClass;

        public static EntityType parseClass(String name) {
            return Arrays.asList(EntityType.values()).stream().filter(e -> e.name().toLowerCase().equalsIgnoreCase(name.toLowerCase())).findFirst().orElse(null);
        }
    }
}

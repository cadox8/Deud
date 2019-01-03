package me.cadox8.deud.saves;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.friends.Fairy;
import me.cadox8.deud.entities.creatures.monsters.Ghost;
import me.cadox8.deud.entities.creatures.monsters.Zombie;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.statics.*;

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

        private String world;
        private String type;
        private int signType;
        private String map;
        private int itemID;
        private String[] text;
        private PlayerData.LocationUtils location;

        public List<String> getText() { return Arrays.asList(text); }
        public Location getLocation() {
            return new Location(location.getX(), location.getY(),location.getDirection());
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
        NPC(Npc.class);

        private Class<? extends Entity> supClass;

        public static EntityType parseClass(String name) {
            return Arrays.asList(EntityType.values()).stream().filter(e -> e.name().toLowerCase().equalsIgnoreCase(name.toLowerCase())).findFirst().get();
        }
    }
}

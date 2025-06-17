package es.cadox8.deud.entities.enums;

import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.creatures.friends.Fairy;
import es.cadox8.deud.entities.creatures.monsters.Ghost;
import es.cadox8.deud.entities.creatures.monsters.Zombie;
import es.cadox8.deud.entities.creatures.npcs.Npc;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.entities.projectile.Arrow;
import es.cadox8.deud.entities.statics.chest.Chest;
import es.cadox8.deud.entities.statics.door.Door;
import es.cadox8.deud.entities.statics.house.House;
import es.cadox8.deud.entities.statics.light.Light;
import es.cadox8.deud.entities.statics.rock.Rock;
import es.cadox8.deud.entities.statics.shop.Shop;
import es.cadox8.deud.entities.statics.sign.Sign;
import es.cadox8.deud.entities.statics.trees.DeadTree;
import es.cadox8.deud.entities.statics.trees.NormalTree;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EntityType {

    PLAYER(Player.class),
    ZOMBIE(Zombie.class),
    GHOST(Ghost.class),
    FAIRY(Fairy.class),
    CHEST(Chest.class),
    LIGHT(Light.class),
    ROCK(Rock.class),
    SIGN(Sign.class),
    NORMALTREE(NormalTree.class),
    DEADTREE(DeadTree.class),
    DOOR(Door.class),
    SHOP(Shop.class),
    NPC(Npc.class),
    ARROW(Arrow.class),
    HOUSE(House.class);

    private final Class<? extends Entity> supClass;

    public static EntityType parseClass(String name) {
        return Arrays.stream(EntityType.values()).filter(e -> e.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}

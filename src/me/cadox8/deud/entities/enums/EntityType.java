package me.cadox8.deud.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.friends.Fairy;
import me.cadox8.deud.entities.creatures.monsters.Ghost;
import me.cadox8.deud.entities.creatures.monsters.Zombie;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.projectile.Arrow;
import me.cadox8.deud.entities.statics.*;
import me.cadox8.deud.entities.statics.chest.Chest;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.entities.statics.trees.DeadTree;
import me.cadox8.deud.entities.statics.trees.NormalTree;

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

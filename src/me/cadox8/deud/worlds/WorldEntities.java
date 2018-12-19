package me.cadox8.deud.worlds;

import lombok.RequiredArgsConstructor;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.entities.creatures.friends.Fairy;
import me.cadox8.deud.entities.creatures.monsters.Ghost;
import me.cadox8.deud.entities.creatures.monsters.Zombie;
import me.cadox8.deud.entities.statics.Chest;
import me.cadox8.deud.entities.statics.Rock;
import me.cadox8.deud.entities.statics.SignEntity;
import me.cadox8.deud.entities.statics.Tree;

import java.util.Arrays;

@RequiredArgsConstructor
public class WorldEntities {

    private final API API;
    private final EntityManager entityManager;

    public void loadEntities() {
        // Static Entities
        entityManager.addEntity(new Tree(API, 130, 250));
        entityManager.addEntity(new Rock(API, 130, 450));
        entityManager.addEntity(new Tree(API, 130, 650));
        entityManager.addEntity(new Rock(API, 130, 850));

        entityManager.addEntity(new SignEntity(API, 500, 150, 0, Arrays.asList("This is a Test")));
        entityManager.addEntity(new SignEntity(API, 500, 250, 1, Arrays.asList("Hello World", "asdasd", "sadasd")));

        entityManager.addEntity(new Chest(API, 600, 120));

        // Creatures
        entityManager.addEntity(new Fairy(API, 135, 100));
        entityManager.addEntity(new Zombie(API, 200, 100));
        entityManager.addEntity(new Ghost(API, 300, 100));
    }
}

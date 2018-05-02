package me.cadox8.deud.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    @Getter @Setter private API API;
    @Getter @Setter private Player player;

    @Getter @Setter private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(API API, Player player) {
        this.API = API;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick() {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.tick();
            e.fixAnimations();
            if (!e.isActive()) it.remove();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        for (Entity e : entities) e.specialRender(g);
        for (Entity e : entities) e.render(g);
        player.postRender(g);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void freezeCreatures() {
        entities.stream().filter(e -> e instanceof Creature).filter(e -> !(e instanceof Player)).forEach(e -> ((Creature)e).setFreeze(true));
    }

    public void killAll() {
        entities.forEach(Entity::kill);
    }
}

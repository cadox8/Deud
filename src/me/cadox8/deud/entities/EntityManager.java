package me.cadox8.deud.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.SignEntity;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    @Getter @Setter private static API API;
    @Getter @Setter private Player player;

    @Getter @Setter private ArrayList<Entity> entities;

    private Comparator<Entity> renderSorter = (Entity a, Entity b) -> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
        return 1;
    };

    public EntityManager(API API, Player player) {
        EntityManager.API = API;
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
            if (!e.isActive()) {
                it.remove();
                entities.remove(e);
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        entities.forEach(e -> e.specialRender(g));
        entities.forEach(e -> e.render(g));
        entities.stream().filter(e -> e instanceof SignEntity).forEach(e -> ((SignEntity) e).signRender(g));
        player.postRender(g);
        //entities.forEach(e -> g.drawRect((int)e.getBounds().getX() + (int)e.getX(), (int)e.getBounds().getY() + (int)e.getY(), (int)e.getBounds().getWidth(), (int)e.getBounds().getHeight()));
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void freezeCreatures() {
        entities.stream().filter(e -> e instanceof Creature).filter(e -> !(e instanceof Player)).forEach(e -> ((Creature)e).setFreeze(true));
    }
    public void freezePlayer() {
        entities.stream().filter(e -> e instanceof Player).findFirst().ifPresent(p -> ((Player)p).setFreeze(true));
    }

    public void removeEntity(Entity en) {
        en.setActive(false);
    }

    public void killAll() {
        entities.forEach(Entity::kill);
    }


    public static void checkAttacks(Entity attacker) {
        checkAttacks(attacker, 0, 0);
    }
    public static void checkAttacks(Entity attacker, float xMove, float yMove) {
        attacker.attackTimer += System.currentTimeMillis() - attacker.lastAttackTimer;
        attacker.lastAttackTimer = System.currentTimeMillis();
        if (attacker.attackTimer < attacker.attackCooldown) {
            if (API.isDebug()) Log.log("Attack in cooldown: " + attacker.attackTimer + "/" + attacker.attackCooldown);
            return;
        }
        if (attacker instanceof Player && ((Player) attacker).getInventory().isActive()) return;
        if (attacker instanceof Player && !API.getMouseManager().isLeftPressed()) return;
        attacker.attackTimer = 0;

        final Entity en = getEntity(attacker, xMove, yMove);
        if (en == null) return;
        en.hurt(attacker);
    }

    public static Entity getEntity(Entity speaker, float xMove, float yMove) {
        Entity finalEntity = null;
        final Rectangle cb = speaker.getCollisionBounds(0, 0);
        final Rectangle ar = new Rectangle();

        final int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        switch (speaker.getDirection()) {
            case 0: // Down
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
                break;
            case 1: // Up
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
                break;
            case 2: // Right
                ar.x = cb.x + cb.width + arSize / 4 - 4;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
            case 3: // Left
                ar.x = cb.x - cb.width + arSize / 4 - 1;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
        }

        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(speaker)) continue;
            if (e.getCollisionBounds(xMove, yMove).intersects(ar)) finalEntity = e;
        }
        return finalEntity;
    }
}

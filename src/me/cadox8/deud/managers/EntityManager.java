package me.cadox8.deud.managers;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.Light;
import me.cadox8.deud.ui.components.base.UIBlock;
import me.cadox8.deud.ui.helpers.AarinArea;
import me.cadox8.deud.ui.helpers.AarinColor;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    @Getter @Setter private static GameAPI gameAPI;
    @Getter @Setter private Player player;

    @Getter @Setter private ArrayList<Entity> entities;

    private final Comparator<Entity> renderSorter = (Entity a, Entity b) -> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
        return 1;
    };

    public EntityManager(@NonNull GameAPI gameAPI, Player player) {
        EntityManager.gameAPI = gameAPI;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick() {
        final Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            final Entity e = it.next();
            e.tick();
            e.fixAnimations();
            if (!e.isActive()) {
                it.remove();
                entities.remove(e);
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g, boolean isDark) {
        entities.forEach(e -> e.preRender(g));

        if (isDark) {
            final UIBlock dark = new UIBlock(gameAPI, AarinColor.BLACK.transparent(150), true);
            dark.setArea(new AarinArea().addPoints(new Point(0, 0), new Point(gameAPI.getWidth(), gameAPI.getHeight())));
            dark.render(g);
        }

        entities.forEach(e -> e.render(g));
        entities.stream().filter(e -> !(e instanceof Player)).filter(e -> !(e instanceof Light)).forEach(e -> e.postRender(g));
        if (isDark) entities.stream().filter(entity -> entity instanceof Light).forEach(e -> e.postRender(g));
        player.postRender(g);
    }

    public void addEntity(Entity e) {
        entities.add(e);
        if (e instanceof Creature) entities.add(((Creature) e).getLight());
    }

    public void freezeAll() {
        freezeCreatures();
    }
    public void freezeCreatures() {
        entities.stream().filter(e -> e instanceof Creature).filter(e -> !(e instanceof Player)).forEach(e -> ((Creature)e).setFreeze(!((Creature)e).isFreeze()));
    }
    public void freezePlayer() {
        entities.stream().filter(e -> e instanceof Player).findFirst().ifPresent(e -> ((Player)e).setFreeze(!((Player)e).isFreeze()));
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
        attacker.setAttackTimer(attacker.getAttackTimer() + System.currentTimeMillis() - attacker.getLastAttackTimer());
        attacker.setLastAttackTimer(System.currentTimeMillis());
        if (attacker.getAttackTimer() < attacker.getAttackCooldown()) {
            if (gameAPI.isDebug()) Log.log("Attack in cooldown: " + attacker.getAttackTimer() + "/" + attacker.getAttackCooldown());
            return;
        }
        if (attacker instanceof Player && ((Player) attacker).getPlayerInventory().isActive()) return;
        if (attacker instanceof Player && !gameAPI.getKeyManager().space) return; //gameAPI.getMouseManager().isLeftPressed()
        attacker.setAttackTimer(0);

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
            case SOUTH: // Down
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
                break;
            case NORTH: // Up
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
                break;
            case EAST: // Right
                ar.x = cb.x + cb.width + arSize / 4 - 4;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
            case WEST: // Left
                ar.x = cb.x - cb.width + arSize / 4 - 1;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
        }

        for (Entity e : gameAPI.getWorld().getEntityManager().getEntities()) {
            if (e.equals(speaker)) continue;
            if (e.getCollisionBounds(xMove, yMove).intersects(ar)) finalEntity = e;
        }
        return finalEntity;
    }
}

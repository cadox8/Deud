package me.cadox8.deud.ai;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.ai.path.Node;
import me.cadox8.deud.ai.path.Path;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class EntityAI {

    protected final GameAPI gameAPI;
    protected final Entity entity;

    private final int delay;
    private final double radius;

    @Getter @Setter protected boolean hasAI = true;

    protected int movementDelay = 0;

    public EntityAI(@NonNull Entity entity, int delay) {
        this(entity, delay, 5);
    }
    public EntityAI(@NonNull Entity entity, int delay, double radius) {
        this.gameAPI = entity.getGameAPI();
        this.entity = entity;
        this.delay = delay;
        this.radius = radius;
    }

    protected Player getPlayerInRadius() {
        return (Player) Utils.getNearbyEntities(entity.getLocation(), radius).stream().filter(e -> e instanceof Player).findAny().orElse(null);
    }

    public abstract void move();

    protected boolean canTrack() {
        return this.getPlayerInRadius() != null || this.hasAI;
    }

    protected List<Node> getPath(Location location) {
        return this.getPath(location.getX(), location.getY());
    }
    protected List<Node> getPath(float xEnd, float yEnd) {
        if (this.getPlayerInRadius() == null) return new ArrayList<>();
        final int[] start = Utils.locationToTile(entity.getX(), entity.getY());
        final int[] end = Utils.locationToTile(xEnd, yEnd);
        final int[][] maze = new int[this.gameAPI.getWorld().getWidth()][this.gameAPI.getWorld().getHeight()];

        int y = 0;

        for (int i = 0; i < maze[0].length; i++) {
            maze[i][y] = this.gameAPI.getWorld().getTiles()[i][y].getId();
            if (i >= maze[0].length) {
                i = 0;
                y++;
            }
        }
        return new Path(maze, start[0], start[1]).findPathTo(end[0], end[1]);
    }

    private boolean isOnDelay() {
        return movementDelay != 0;
    }

    protected void randomMove() {
        if (!(this.entity instanceof Creature)) return;
        final Creature creature = (Creature) this.entity;
        while (isOnDelay()) {
            this.movementDelay--;
            return;
        }
        if (creature.isFreeze()) return;

        this.movementDelay = delay;
        final int direction = new Random().nextInt(9);

        switch (direction) {
            case 0:
                creature.setYMove(creature.getSpeed());
                break;
            case 1:
                creature.setYMove(-creature.getSpeed());
                break;
            case 2:
                creature.setXMove(creature.getSpeed());
                break;
            case 3:
                creature.setXMove(-creature.getSpeed());
                break;
            default:
                return;
        }
        entity.setDirection(direction);
    }
}

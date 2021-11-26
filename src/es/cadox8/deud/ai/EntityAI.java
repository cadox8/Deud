package es.cadox8.deud.ai;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.creatures.Creature;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.entities.enums.Direction;
import es.cadox8.deud.utils.Utils;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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



    private boolean isOnDelay() {
        return movementDelay != 0;
    }

    protected void randomMove() {
        if (!(this.entity instanceof Creature)) return;
        if (this.isOnDelay()) {
            this.movementDelay--;
            return;
        }
        final Creature creature = (Creature) this.entity;
        if (creature.isFreeze()) return;

        this.movementDelay = delay;
        final Direction direction = Direction.randomDirection();

        switch (direction) {
            case SOUTH: // 0 - 3
                creature.setYMove(creature.getSpeed());
                break;
            case NORTH:
                creature.setYMove(-creature.getSpeed());
                break;
            case EAST:
                creature.setXMove(creature.getSpeed());
                break;
            case WEST:
                creature.setXMove(-creature.getSpeed());
                break;
            default:
                return;
        }
        entity.setDirection(direction);
    }

    protected Creature getCreature() {
        return (Creature) this.entity;
    }
}

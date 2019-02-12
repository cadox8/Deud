package me.cadox8.deud.ai.entities;

import me.cadox8.deud.ai.AI;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.statics.StaticEntity;

public class StaticAPI extends AI {

    private StaticEntity staticEntity;

    private int tempDelay;
    private int direction = 0;

    public StaticAPI(GameAPI GameAPI, StaticEntity staticEntity, float speed, int delay) {
        super(GameAPI, staticEntity, speed, delay);
        this.staticEntity = staticEntity;
        tempDelay = delay;
    }

    @Override
    public void getMove() {}

    @Override
    public boolean isTracking() {
        return false;
    }
}

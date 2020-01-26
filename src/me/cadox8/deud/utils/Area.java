package me.cadox8.deud.utils;

import lombok.RequiredArgsConstructor;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Area {

    private final GameAPI gameAPI;
    private final Point point1, point2;



    public List<Entity> getEntities() {
        final List<Entity> entities = new ArrayList<>();

        return entities;
    }
}

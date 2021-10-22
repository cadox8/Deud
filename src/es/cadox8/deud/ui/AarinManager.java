/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 *
 */

package es.cadox8.deud.ui;

import lombok.Getter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class AarinManager {

    @Getter private final ArrayList<AarinUI> objects;

    /**
     * The constructor of the class
     */
    public AarinManager() {
        objects = new ArrayList<>();
    }

    /**
     * Adds an AarinUI
     * @see AarinUI
     *
     * @param object The AarinUI
     */
    public void addObject(AarinUI object) {
        synchronized (objects) {
            objects.add(object);
        }
    }

    public void setObject(int slot, AarinUI object) {
        synchronized (objects) {
            objects.set(slot, object);
        }
    }

    /**
     * Removes an AarinUI
     * @see AarinUI
     *
     * @param object The AarinUI
     */
    public void removeObject(AarinUI object) {
        removeObject(object.getComponentId());
    }

    /**
     * Removes an AarinUI
     * @see AarinUI
     *
     * @param componentID The componentID
     */
    public void removeObject(long componentID) {
        synchronized (objects) {
            objects.removeIf(AarinUI -> AarinUI.getComponentId() == componentID);
        }
    }

    /**
     * Removes all the objects
     */
    public void removeAllObjects() {
        if (objects.isEmpty()) return;
        synchronized (objects) {
            final Iterator<AarinUI> it = objects.iterator();
            while (it.hasNext()) it.remove();
        }
    }

    /**
     * Gets an specific object
     *
     * @param componentID The ID of the component to search
     */
    public AarinUI getObject(long componentID) {
        return objects.stream().filter(c -> c.getComponentId() == componentID).findAny().orElse(null);
    }

    public void tick() {
        synchronized (objects) {
            try {
                objects.stream().filter(AarinUI::isEnabled).forEach(AarinUI::tick);
            } catch (ConcurrentModificationException e) {}
        }
    }

    public void render(Graphics g) {
        synchronized (objects) {
            try {
                objects.stream().filter(AarinUI::isEnabled).forEach(o -> o.render(g));
            } catch (ConcurrentModificationException e) {}
        }
    }

    public void onMouseMove(MouseEvent e) {
        objects.stream().filter(AarinUI::isEnabled).filter(AarinUI::isHoverable).forEach(o -> o.onMouseMove(e));
    }
    public void onMouseClicked(MouseEvent e) {
        objects.stream().filter(AarinUI::isEnabled).filter(AarinUI::isClickable).filter(AarinUI::isHoverable).filter(AarinUI::isHovering).forEach(o -> o.onMouseClicked(e));
    }
}

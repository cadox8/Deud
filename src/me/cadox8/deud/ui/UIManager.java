package me.cadox8.deud.ui;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private GameAPI gameAPI;
    @Getter @Setter private ArrayList<UIObject> objects;

    public UIManager(@NonNull GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        objects = new ArrayList<>();
    }

    public void tick() {
        objects.forEach(UIObject::tick);
    }

    public void render(Graphics g) {
        objects.forEach(o -> o.render(g));
    }

    public void onMouseMove(MouseEvent e) {
        objects.forEach(o -> o.onMouseMove(e));
    }

    public void onMouseRelease(MouseEvent e) {
        objects.forEach(o -> o.onMouseRelease(e));
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }
    public void removeObject(UIObject o) {
        objects.remove(o);
    }
}

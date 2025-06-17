package es.cadox8.deud.ui;

import lombok.Getter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class UiManager {

    @Getter private final List<UiComponent> components;

    private final Comparator<UiComponent> componentOrder = Comparator.comparingInt(UiComponent::getLayer);

    public UiManager() {
        this.components = new ArrayList<>();
    }

    public synchronized void addComponent(UiComponent component) {
        this.components.add(component);
        this.components.sort(this.componentOrder);
    }

    public synchronized void setComponent(int index, UiComponent component) {
        this.components.set(index, component);
        this.components.sort(this.componentOrder);
    }

    public synchronized void changeEnableStatus(long componentId, boolean enabled) {
        Objects.requireNonNull(this.components.stream().filter(c -> c.getComponentId() == componentId).findAny().orElse(null)).setEnabled(enabled);
    }

    public synchronized void tick() {
        this.components.stream().filter(UiComponent::isEnabled).forEach(UiComponent::tick);
    }

    public synchronized void render(Graphics g) {
        this.components.stream().filter(UiComponent::isEnabled).forEach(c -> c.render(g));
    }

    public void onMouseDragged(MouseEvent e) {
        this.components.forEach(o -> o.onMouseDragged(e));
    }
    public void onMouseMove(MouseEvent e) {
        this.components.forEach(o -> o.onMouseMove(e));
    }
    public void onMouseClicked(MouseEvent e) {
        this.components.forEach(o -> o.onMouseClicked(e));
    }
}

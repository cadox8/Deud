package es.cadox8.deud.ui.components.inventory;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.items.Item;
import es.cadox8.deud.ui.helpers.AarinArea;
import lombok.Getter;
import es.cadox8.deud.ui.components.button.UIImageButton;
import es.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UIPlayerInventory extends UIInventory {

    @Getter private final List<UIImageButton> items;
    private final Point itemStart = new Point(676, 130);

    public UIPlayerInventory(GameAPI gameAPI, BufferedImage image) {
        super(gameAPI, new Point(650, 50), image);
        this.items = new ArrayList<>();
    }

    @Override
    public void tick() {
        super.tick();
        this.items.forEach(UIImageButton::tick);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.items.forEach(i -> i.render(g));
        this.items.stream().filter(UIImageButton::isHovering).findAny().ifPresent(uiImageButton -> this.displayItemInfo((String) uiImageButton.getMetadata().get("display"), 855, 646));
    }

    public void addItems(List<Item> items) {
        this.items.clear();
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);

        items.forEach(i -> {
            if (ySlot.get() > 6) return;

            final UIImageButton item = new UIImageButton(this.gameAPI, i.getTexture(), () -> {
                Log.log(i.getName());
            });
            item.getMetadata().put("display", i.getName() + " x" + i.getCount());
            item.setArea(new AarinArea().addPoints(new Point((int)(this.itemStart.getX() + (xSlot.get() * 64) + 1), (int)(this.itemStart.getY() + (ySlot.get() * 64) + 1)), new Point(i.getTexture().getWidth(), i.getTexture().getHeight())));
            this.items.add(item);
        });
    }

    @Override
    public void onMouseMove(MouseEvent e) {
        this.items.forEach(i -> i.setHovering(i.getArea().isInside(e.getPoint())));
    }
    @Override
    public void onMouseClicked(MouseEvent e) {
        this.items.stream().filter(UIImageButton::isHovering).forEach(UIImageButton::onClick);
    }
}

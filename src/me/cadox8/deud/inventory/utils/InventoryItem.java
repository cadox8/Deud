package me.cadox8.deud.inventory.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.nysvaui.ClickListener;
import me.cadox8.deud.nysvaui.components.images.UIImageButton;
import me.cadox8.deud.nysvaui.helpers.UIDimension;
import me.cadox8.deud.utils.Log;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class InventoryItem {

    private final GameAPI gameAPI;
    private int id;
    private Item item;
    private UIDimension dimension;
    private ClickListener clicker;

    public UIImageButton generateUIComponent() {
        final UIImageButton button = new UIImageButton(gameAPI, item.getTexture(), clicker);
        button.setUiDimension(dimension);
        return button;
    }

}

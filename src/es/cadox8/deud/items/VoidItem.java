package es.cadox8.deud.items;

import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.items.objects.StoneItem;
import lombok.NonNull;

public class VoidItem extends Item {

    public VoidItem() {
        super(Assets.bug, -1, "Void", ItemType.NONE);
    }

    @Override
    public void use(@NonNull Player p) {}

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new StoneItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}

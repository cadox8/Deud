package es.cadox8.deud.items.objects;

import es.cadox8.deud.items.Item;
import es.cadox8.deud.graphics.textures.Assets;

public class StoneItem extends ObjectItem {

    public StoneItem() {
        super(Assets.stone, 1, "Stone");
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new StoneItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}

package es.cadox8.deud.items.food;

import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.items.Item;

public class ChickenItem extends FoodItem {

    public ChickenItem() {
        super(Assets.chicken, 4, "Chicken", 3);
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new ChickenItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}

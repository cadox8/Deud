package es.cadox8.deud.inventory.statics;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.items.Items;

public class ChestInventory extends StaticInventory {

    public ChestInventory(GameAPI gameAPI, int size) {
        super(gameAPI);

        addItems(Items.SWORD.item(), Items.CHICKEN.item(), Items.WOOD.item());

        //loadBaseInventory(676, 130, GUI.chest);

        gameAPI.getMouseManager().setAarinManager(getAarinManager());
    }

    @Override
    public void tick() {
        if (!isActive()) return;
        getAarinManager().tick();
    }

/*    @Override
    public void render(Graphics g) {
        if (!isActive()) return;
        getNysvaManager().render(g);
        hoverSelector(g, 855, 646);
    }*/
}

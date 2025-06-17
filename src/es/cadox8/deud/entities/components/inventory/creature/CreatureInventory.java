package es.cadox8.deud.entities.components.inventory.creature;

import es.cadox8.deud.entities.components.inventory.Inventory;
import es.cadox8.deud.entities.creatures.Creature;
import es.cadox8.deud.items.Item;

import java.awt.*;
import java.util.HashMap;

public class CreatureInventory extends Inventory {

    protected final Creature creature;

    protected final HashMap<Equipment, Item> equipmentList;

    public CreatureInventory(Creature creature) {
        super(InventoryType.CREATURE);

        this.creature = creature;
        this.equipmentList = new HashMap<>();
    }


    @Override
    public void open() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void setItemInHand(Item item) {
        this.setEquipment(Equipment.HAND, item);
    }

    public Item getItemInHand() {
        return this.getEquipment(Equipment.HAND);
    }

    public void setEquipment(Equipment equipment, Item item) {
        this.equipmentList.put(equipment, item);
    }

    public Item getEquipment(Equipment equipment) {
        return this.equipmentList.get(equipment);
    }

    public HashMap<Equipment, Item> getEquipments() {
        return this.equipmentList;
    }

    public enum Equipment {
        HAND, CHESTPLATE, BOOTS, RING
    }
}

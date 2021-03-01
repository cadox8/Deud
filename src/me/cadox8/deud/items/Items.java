package me.cadox8.deud.items;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.items.food.ChickenItem;
import me.cadox8.deud.items.objects.KeyItem;
import me.cadox8.deud.items.objects.MapItem;
import me.cadox8.deud.items.objects.StoneItem;
import me.cadox8.deud.items.objects.WoodItem;
import me.cadox8.deud.items.potions.HealthPotion;
import me.cadox8.deud.items.weapons.HandItem;
import me.cadox8.deud.items.weapons.SwordItem;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RequiredArgsConstructor
public enum Items {

    WOOD(0, WoodItem.class),
    STONE(1, StoneItem.class),
    KEY(2, KeyItem.class),
    MAP(3,MapItem.class),
    CHICKEN(4, ChickenItem.class),
    HEALTH_POTION(5, HealthPotion.class),
    SWORD(6, SwordItem.class),
    HAND(7, HandItem.class);

    @Getter private final int id;
    private final Class<? extends Item> item;

    public Item item() {
        try {
            return this.item.getDeclaredConstructor().newInstance();
        } catch(IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Item getItem(int id) {
        return Arrays.stream(Items.values()).filter(i -> i.getId() == id).findAny().orElse(null).item();
    }
}

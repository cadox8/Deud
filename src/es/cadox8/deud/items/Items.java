package es.cadox8.deud.items;

import es.cadox8.deud.items.food.ChickenItem;
import es.cadox8.deud.items.objects.KeyItem;
import es.cadox8.deud.items.objects.MapItem;
import es.cadox8.deud.items.objects.StoneItem;
import es.cadox8.deud.items.objects.WoodItem;
import es.cadox8.deud.items.potions.HealthPotion;
import es.cadox8.deud.items.weapons.HandItem;
import es.cadox8.deud.items.weapons.SwordItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public enum Items {

    WOOD(0, WoodItem.class),
    STONE(1, StoneItem.class),
    KEY(2, KeyItem.class),
    MAP(3, MapItem.class),
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
        return Objects.requireNonNull(Arrays.stream(Items.values()).filter(i -> i.getId() == id).findAny().orElse(null)).item();
    }
}

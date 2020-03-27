package me.cadox8.deud.items;

import lombok.Data;
import lombok.Getter;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.items.food.ChickenItem;
import me.cadox8.deud.items.objects.*;
import me.cadox8.deud.items.potions.HealthPotion;
import me.cadox8.deud.items.potions.PotionItem;
import me.cadox8.deud.items.weapons.HandItem;
import me.cadox8.deud.items.weapons.SwordItem;
import me.cadox8.deud.items.weapons.WeaponItem;

@Data
public class Items {

    //Bug
    @Getter private static final ObjectItem bugItem = new BugItem(Assets.bug, 5, "3RR0R");

    //Objects
    @Getter private static final ObjectItem woodItem = new WoodItem(Assets.wood, 0, "Wood");
    @Getter private static final ObjectItem rockItem = new RockItem(Assets.stone, 1, "Rock");
    @Getter private static final ObjectItem keyItem = new KeyItem(Assets.key, 2, "Key");
    @Getter private static final ObjectItem mapItem = new MapItem(null, 7, "Map");

    //Food
    @Getter private static final ChickenItem chickenItem = new ChickenItem(Assets.food, 3, "Chicken", 3);

    //Weapons
    @Getter private static final WeaponItem hand = new HandItem(Assets.hand, 4, "Hand", 1);
    @Getter private static final WeaponItem sword = new SwordItem(Assets.sword, 8, "Sword", 5);

    // Potions
    @Getter private static final PotionItem healthPotion = new HealthPotion(6, 1);
}

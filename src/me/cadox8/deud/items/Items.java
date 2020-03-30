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
    @Getter private static final ObjectItem bugItem = new BugItem(Assets.bug, 0, "3RR0R");

    //Objects
    @Getter private static final ObjectItem woodItem = new WoodItem(Assets.wood, 1, "Wood");
    @Getter private static final ObjectItem rockItem = new RockItem(Assets.stone, 2, "Rock");
    @Getter private static final ObjectItem keyItem = new KeyItem(Assets.key, 3, "Key");
    @Getter private static final ObjectItem mapItem = new MapItem(null, 4, "Map");

    //Food
    @Getter private static final ChickenItem chickenItem = new ChickenItem(Assets.food, 5, "Chicken", 3);

    //Weapons
    @Getter private static final WeaponItem hand = new HandItem(Assets.hand, 6, "Hand", 1);
    @Getter private static final WeaponItem sword = new SwordItem(Assets.sword, 7, "Sword", 5);

    // Potions
    @Getter private static final PotionItem healthPotion = new HealthPotion(8, 1);
}

package me.cadox8.deud.saves;

import lombok.Getter;

public class PlayerData {

    @Getter private int Money;
    @Getter private int Health;

    @Getter private String[] Location;
    //@Getter private String[] Inventory;
}

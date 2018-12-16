package me.cadox8.deud.saves;

import lombok.Data;
import lombok.Getter;

@Data
public class PlayerData {

    private int Money;
    private int Health;

    private String[] Location;
    //@Getter private String[] Inventory;
}

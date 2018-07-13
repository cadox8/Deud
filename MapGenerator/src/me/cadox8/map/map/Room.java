package me.cadox8.map.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Room {

    @Getter private int roomID;
    @Getter private String layout;
}

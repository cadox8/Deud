package me.cadox8.deud.settings;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Data
public class Settings {

    private float volume;
    private int windows;
    private int mode;

    public Settings() {
        volume = 1.0f;
        windows = 1;
        mode = 0;
    }

    public Gamemode getGamemode() {
        return parseGamemode(mode);
    }

    private Gamemode parseGamemode(int gamemode) {
        return Arrays.asList(Gamemode.values()).stream().filter(g -> g.getGamemode() == gamemode).findFirst().orElse(Gamemode.SURVIVAL);
    }


    @RequiredArgsConstructor
    public enum Gamemode {
        SURVIVAL(0), CREATIVE(1);

        @Getter private final int gamemode;
    }
}

package me.cadox8.deud.settings;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Data
public class Settings {

    private float volume;
    private int windows;

    public Settings() {
        volume = 1.0f;
        windows = 1;
    }
}

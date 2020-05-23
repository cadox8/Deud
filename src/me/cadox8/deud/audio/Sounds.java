package me.cadox8.deud.audio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Sounds {

    // UI Music
    MENU("menu", -22f),

    // Towns Music
    TOWN_MUSIC("town", -35f),

    // Utils Music
    ENTITY_WALK_GRASS("entity_walk_grass", -22f);

    private final String name;
    private final float volume;

    public Sound getSound() {
        return new Sound(name, volume);
    }

    public void play() {
        getSound().play();
    }

    public void playLoop() {
        getSound().playLoop();
    }
}

package me.cadox8.deud.audio;

import lombok.Getter;

@Getter
public enum Sounds {

    // UI Music
    MENU("menu", 0),

    // Arena Music
    TOWN_MUSIC("town", 15f),

    // Misc Music
    ENTITY_WALK_GRASS("entity_walk_grass", 0);

    private final String name;
    private final float volume;

    private final Sound sound;

    Sounds(final String name, final float volume) {
        this.name = name;
        this.volume = -22f + (-volume);

        this.sound = new Sound(name, volume);
    }

    public void play() {
        getSound().play();
    }

    public void playLoop() {
        getSound().playLoop();
    }
}

package me.cadox8.deud.audio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Sound {

    NONE("", 0),
    ENTITY_WALK_GRASS("entity_walk_grass", 5),
    TOWN("town", 0.5f);

    @Getter private String name;
    @Getter private float volume;

    private final SoundClip sound = new SoundClip();

    public void playSound() {
        sound.playSound(getName());
    }
}

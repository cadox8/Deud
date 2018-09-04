package me.cadox8.deud.audio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Sound {

    NONE("", 0),
    ENTITY_WALK_GRASS("entity_walk_grass", 5);

    @Getter private String name;
    @Getter private float volume;

    public void playSound() {
        new SoundClip().playSound(getName());
    }
}

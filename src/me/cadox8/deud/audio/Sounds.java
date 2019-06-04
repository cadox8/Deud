package me.cadox8.deud.audio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sounds {

    NONE("", 0),
    ENTITY_WALK_GRASS("entity_walk_grass", 5),
    TOWN("town", 0.5f);

    @Getter private final String name;
    @Getter private final float volume;

    public void playSound() {
         new Sound(getName(), getVolume()).playSound();
    }
}

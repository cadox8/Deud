package me.cadox8.deud.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.tiles.Tile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

@AllArgsConstructor
public enum Sound {

    NONE("", 0),
    ENTITY_WALK_GRASS("entity_walk_grass", 5),
    ENTITY_WALK_STONE("entity_walk_stone", 5);

    @Getter private String name;
    @Getter private float volume;

    public static Sound getWalkSound(Tile tile) {
        switch (tile.getId()) {
            case 0:
            case 1:
                return ENTITY_WALK_GRASS;
            case 4:
            case 5:
                return NONE;
            default:
                return NONE;
        }
    }


    private static Clip clip;
    public static void playSound(final Sound sound) {
        try {
            if (sound.equals(NONE)) return;
            if (clip.isRunning()) return;

            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResourceAsStream("/sounds/" + sound.getName() + ".wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

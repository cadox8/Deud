package me.cadox8.deud.config;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class Config {

    private final Sounds sounds = new Sounds();

    @Data
    public static class Sounds {
        private final float masterVolume = 1f;

        private final float musicVolume = 1f;
        private final float entitiesVolume = 1f;
    }
}

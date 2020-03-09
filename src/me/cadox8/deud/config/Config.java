package me.cadox8.deud.config;

import lombok.Data;

@Data
public class Config {

    private boolean fullScreen = true;

    private float masterVolume = 1;

    private float musicVolume = 1;
    private float entitiesVolume = 1;

    public Config() {}
}

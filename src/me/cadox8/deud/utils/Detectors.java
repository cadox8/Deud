package me.cadox8.deud.utils;

import lombok.Getter;

public class Detectors {

    @Getter private boolean w10 = true;
    @Getter private boolean java1_8 = true;

    public Detectors() {
        if (!System.getProperty("os.name").contains("10")) w10 = false;
        if (!System.getProperty("java.runtime.version").contains("1.8")) java1_8 = false;
    }
}

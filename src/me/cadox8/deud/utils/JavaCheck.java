package me.cadox8.deud.utils;

import lombok.Getter;

public class JavaCheck {

    @Getter private boolean java1_8 = false;

    private static final double MIN_JAVA_VERSION = 1.8;

    public JavaCheck() {
        double version = Double.valueOf(System.getProperty("java.runtime.version").split("\\.")[0]);
        Log.log("Java Version: " + version);
        if (version >= MIN_JAVA_VERSION) java1_8 = true;
    }
}

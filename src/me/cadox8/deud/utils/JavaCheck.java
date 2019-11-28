package me.cadox8.deud.utils;

public class JavaCheck {

    private boolean javaVersion = false;

    private static final double MIN_JAVA_VERSION = 1.9;

    public JavaCheck() {
        final double version = Double.parseDouble(System.getProperty("java.runtime.version").split("\\.")[0] + "." + System.getProperty("java.runtime.version").split("\\.")[1]);
        Log.log("Java Version: " + version);
        if (version >= MIN_JAVA_VERSION) javaVersion = true;
    }

    public boolean hasJavaVersion() {
        return javaVersion;
    }
}

package me.cadox8.deud.utils;

import me.cadox8.deud.Launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater {

    public static boolean timeToUpdate(){
        return !getWebVersion().equalsIgnoreCase(Launcher.VERSION);
    }

    public static String getWebVersion(){
        try {
            final String version = new BufferedReader(new InputStreamReader(new URL("https://cadox8.github.io/Deud/version.txt").openStream())).readLine().split("%")[1];
            Log.log("Web Version: " + version + " || Game Version: " + Launcher.VERSION);
            return version;
        } catch (IOException e){}
        return Launcher.VERSION;
    }
}

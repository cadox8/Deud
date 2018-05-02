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
            URL u = new URL("https://cadox8.github.io/Deud/version.txt");
            String version = new BufferedReader(new InputStreamReader(u.openStream())).readLine().split("%")[1];
            Log.log("Version: " + version);
            return version;
        } catch (IOException e){}
        return Launcher.VERSION;
    }
}

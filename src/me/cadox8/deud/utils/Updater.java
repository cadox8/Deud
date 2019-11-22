package me.cadox8.deud.utils;

import me.cadox8.deud.Launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    public static boolean timeToUpdate(){
        return !getWebVersion().equalsIgnoreCase(Launcher.VERSION);
    }

    public static String getWebVersion(){
        try {
            URLConnection connection = new URL("https://cadox8.github.io/Deud/version.txt").openConnection();
            final String redirect = connection.getHeaderField("Location");
            if (redirect != null) connection = new URL(redirect).openConnection();
            final String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            Log.log("Web Version: " + version + " || Game Version: " + Launcher.VERSION);
            return version;
        } catch (IOException e) {
            return Launcher.VERSION;
        }
    }
}

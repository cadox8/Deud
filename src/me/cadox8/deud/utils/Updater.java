package me.cadox8.deud.utils;

import lombok.Getter;
import me.cadox8.deud.Launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    @Getter private static String webVersion;

    public static boolean timeToUpdate(){
        fetchWebVersion();
        final String[] vParts = webVersion.split(" ");
        final String[] vParts2 = Launcher.VERSION.split(" ");

        if (vParts[0].equalsIgnoreCase(vParts2[0])) {
            if (vParts[1].equalsIgnoreCase(vParts2[1])) {
                return true;
            }
        }
        return false;
    }

    private static void fetchWebVersion(){
        try {
            URLConnection connection = new URL("https://cadox8.github.io/Deud/version.txt").openConnection();
            final String redirect = connection.getHeaderField("Location");
            if (redirect != null) connection = new URL(redirect).openConnection();
            final String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            Log.log("Web Version: " + version + " || Game Version: " + Launcher.VERSION);
            webVersion = version;
        } catch (IOException e) {
            webVersion = Launcher.VERSION;
        }
    }
}

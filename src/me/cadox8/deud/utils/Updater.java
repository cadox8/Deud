package me.cadox8.deud.utils;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.cadox8.deud.Launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Updater {

    public static boolean checkForUpdate() {
        Log.system("Web Version: " + latestVersion().getVersion());
        Log.system("Game Version: " + Launcher.VERSION);
        return webVersion().getLatest() > Launcher.VERSION_ID;
    }

    public static Versions webVersion() {
        try {
            URLConnection connection = new URL("https://cadox8.es/deud/versions.json").openConnection();
            final String redirect = connection.getHeaderField("Location");
            if (redirect != null) connection = new URL(redirect).openConnection();
            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String lines;
            while ((lines = br.readLine()) != null) sb.append(lines);

            return new GsonBuilder().setPrettyPrinting().create().fromJson(sb.toString(), Versions.class);
        } catch (IOException e) {
            final Versions versions = new Versions();
            versions.latest = Launcher.VERSION_ID;
            final Versions.VersionData[] d = new Versions.VersionData[1];
            d[0] = new Versions.VersionData(Launcher.VERSION_ID, Launcher.VERSION);
            versions.older = d;
            return versions;
        }
    }

    private static Versions.VersionData getVersion(int id) {
        return Arrays.stream(webVersion().getOlder()).filter(v -> v.id == id).findAny().orElse(new Versions.VersionData(Launcher.VERSION_ID, Launcher.VERSION));
    }

    public static Versions.VersionData latestVersion() {
        return getVersion(webVersion().getLatest());
    }

    @Data
    public static class Versions {

        private int latest;
        private VersionData[] older;

        @Data
        @AllArgsConstructor
        public static class VersionData {
            private int id;
            private String version;
        }
    }
}

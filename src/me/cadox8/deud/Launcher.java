package me.cadox8.deud;

import lombok.Getter;
import me.cadox8.deud.exceptions.JavaVersionException;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.utils.Discord;
import me.cadox8.deud.utils.JavaCheck;
import me.cadox8.deud.utils.Log;

import java.io.File;

public class Launcher {

    public static final String VERSION = "Alpha v0.6.0";
    public static final String GAME_FILE = "C:" + File.separator + "Deud" + File.separator;

    @Getter private static Game game;
    @Getter private static Discord discord;

    public static void main(String[] args) {
        try {
            if (!new JavaCheck().isJavaVersion()) throw new JavaVersionException("Deud needs Java 1.9 or above to run");
        } catch (JavaVersionException e) {
            Log.log(Log.LogType.DANGER, e.getMessage());
            return;
        }

        FileUtils.checkFile();

        discord = new Discord();

        game = new Game("Deud" + " ~~ " + VERSION, 1250, 800);
        game.start();
    }
}

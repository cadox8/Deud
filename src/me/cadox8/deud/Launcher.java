package me.cadox8.deud;

import lombok.Getter;
import me.cadox8.deud.exceptions.JavaVersionException;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.saves.PlayerData;
import me.cadox8.deud.utils.JavaCheck;
import me.cadox8.deud.utils.Log;

import java.io.File;

public class Launcher {

    public static final String VERSION = "Alpha v0.5.5";
    public static final String GAME_FILE = "C:" + File.separator + "Deud" + File.separator;

    @Getter private static JavaCheck javaCheck = new JavaCheck();

    @Getter private static PlayerData playerData;

    public static void main(String[] args) {
        try {
            if (!javaCheck.isJava1_8()) throw new JavaVersionException("Deud needs Java 1.8 or above to run");
        } catch (JavaVersionException e) {
            Log.log(Log.LogType.DANGER, e.getMessage());
            return;
        }

        FileUtils.checkFile();
        playerData = FileUtils.load();

        Game game = new Game("Deud" + " ~~ " + VERSION, 800, 600);
        game.start();
    }
}

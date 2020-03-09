package me.cadox8.deud;

import lombok.Getter;
import me.cadox8.deud.exceptions.JavaVersionException;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.utils.Discord;
import me.cadox8.deud.utils.JavaCheck;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.io.File;

public class Launcher {

    public static final int VERSION_ID = 1;

    public static final String VERSION = "Release 1.0 - Beta";
    public static final String GAME_FILE = "." + File.separator + "Deud" + File.separator;

    @Getter private static Discord discord;

    public static void main(String[] args) {
        try {
            if (!new JavaCheck().hasJavaVersion()) throw new JavaVersionException("Deud needs Java 1.9 or above to run");
        } catch (JavaVersionException e) {
            Log.danger(e.getMessage());
            return;
        }
        FileUtils.checkFile();
        discord = new Discord();
        //new Game("Deud" + " ~~ " + VERSION, 1250, 800).start();
        new Game("Deud" + " ~~ " + VERSION, getDimension().width, getDimension().height).start();
    }

    private static Dimension getDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}

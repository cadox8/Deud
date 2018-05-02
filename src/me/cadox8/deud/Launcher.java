package me.cadox8.deud;

import me.cadox8.deud.exceptions.JavaVersionException;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.utils.Detectors;
import me.cadox8.deud.utils.Log;

import java.io.File;

public class Launcher {

    public static final String VERSION = "Alpha v0.5.5";
    public static final String GAME_FILE = "C:" + File.separator + "Deud" + File.separator;

    public static final Detectors d = new Detectors();

    public static void main(String[] args) {
        try {
            if (!d.isJava1_8()) throw new JavaVersionException("Deud needs Java 1.8 to run");
        } catch (JavaVersionException e) {
            Log.log(Log.LogType.DANGER, e.getMessage());
            return;
        }

        Game game = new Game("Deud" + " ~~ " + VERSION, 800, 600);
        game.start();
    }
}

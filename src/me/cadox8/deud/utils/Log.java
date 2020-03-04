package me.cadox8.deud.utils;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {

    @RequiredArgsConstructor
    public enum LogType {
        SUCCESS("[Success]"),
        NORMAL(""),
        WARNING("[Warning]"),
        DANGER("[Danger]"),
        DEBUG("[Debug]");

        @Getter private final String prefix;
    }

    private static final ColoredPrinter debug = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.MAGENTA).build();
    private static final ColoredPrinter danger = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.RED).build();
    private static final ColoredPrinter warning = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.YELLOW).build();
    private static final ColoredPrinter normal = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.WHITE).build();
    private static final ColoredPrinter success = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.GREEN).build();

    /**
     * Logs the info as Debug
     *
     * @param info The object to be logged
     */
    public static void log(Object info){
        log(debug, LogType.DEBUG, info);
    }

    /**
     * Logs the info as Danger
     *
     * @param info The object to be logged
     */
    public static void danger(Object info) {
        log(danger, LogType.DANGER, info);
    }

    /**
     * Logs the info as Warning
     *
     * @param info The object to be logged
     */
    public static void warning(Object info) {
        log(warning, LogType.WARNING, info);
    }

    /**
     * Logs the info as Normal
     *
     * @param info The object to be logged
     */
    public static void normal(Object info) {
        log(normal, LogType.NORMAL, info);
    }

    /**
     * Logs the info as Success
     *
     * @param info The object to be logged
     */
    public static void success(Object info) {
        log(success, LogType.SUCCESS, info);
    }

    /**
     * Logs the info as the type you select
     * @see LogType
     *
     * @param type The log type
     * @param text The object to be logged
     */
    private static void log(ColoredPrinter printer, LogType type, Object text){
        final String time = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)) + "]";
        final String log = time + type.getPrefix() + " " + text;
        printer.setTimestamping(false);
        printer.println(log);
    }
}

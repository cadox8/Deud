package me.cadox8.deud.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {

    @AllArgsConstructor
    public enum LogType {
        SUCCESS("[Success]", "\u001B[32m"),
        NORMAL("", ""),
        WARNING("[Warning]", "\u001B[33m"),
        DANGER("[Danger]", "\u001B[31m"),
        DEBUG("[Debug]", "\u001B[36m");

        @Getter private String prefix;
        @Getter private String color;
    }

    /**
     * Logs the info as Debug
     *
     * @param info The object to be logged
     */
    public static void log(Object info){
        log(LogType.DEBUG, info);
    }

    /**
     * Logs the info as Danger
     *
     * @param info The object to be logged
     */
    public static void danger(Object info) {
        log(LogType.DANGER, info);
    }

    /**
     * Logs the info as Warning
     *
     * @param info The object to be logged
     */
    public static void warning(Object info) {
        log(LogType.WARNING, info);
    }

    /**
     * Logs the info as Normal
     *
     * @param info The object to be logged
     */
    public static void normal(Object info) {
        log(LogType.NORMAL, info);
    }

    /**
     * Logs the info as Success
     *
     * @param info The object to be logged
     */
    public static void success(Object info) {
        log(LogType.SUCCESS, info);
    }

    /**
     * Logs the info as the type you select
     * @see LogType
     *
     * @param type The log type
     * @param text The object to be logged
     */
    public static void log(LogType type, Object text){
        String time = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)) + "]";
        String log;

        if (System.getProperty("os.name").contains("10")) {
            log = time + type.getColor() + type.getPrefix() + " \u001B[0m" + text;
        } else {
            log = time + type.getPrefix() + " " + text;
        }
        System.out.println(log);
    }
}

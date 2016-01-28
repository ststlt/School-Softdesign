package com.softdesign.school.utils;

import android.util.Log;

public class Lg {

    private static final String PREFIX = "School ";
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED; // TODO вкл/выкл режим логгирования из конфига
//        return true;
        return false;
    }

    /** Получено сообщение уровня VERBOSE из внешнего класса, где:
     * - tag - имя класса;
     * - text - содержание. */
    public static void v (String tag, String text) {
        helper(Log.VERBOSE, tag, text);
    }

    /** Получено сообщение уровня DEBUG из внешнего класса, где:
     * - tag - имя класса;
     * - text - содержание. */
    public static void d (String tag, String text){
        helper(Log.DEBUG, tag, text);
    }

    /** Получено сообщение уровня INFO из внешнего класса, где:
     * - tag - имя класса;
     * - text - содержание. */
    public static void i (String tag, String text){
        helper(Log.INFO, tag, text);
    }

    /** Получено сообщение уровня WARN из внешнего класса, где:
     * - tag - имя класса;
     * - text - содержание. */
    public static void w (String tag, String text) {
        helper(Log.WARN, tag, text);
    }

    /** Получено сообщение уровня ERROR из внешнего класса, где:
     * - tag - имя класса;
     * - text - содержание. */
    public static void e (String tag, String text) {
        helper(Log.ERROR, tag, text);
    }

    /** Обработка ошибки перед ее выводом, где:
     * - level - уровень (Error);
     * - source - класс источника;
     * - msg - содержание.*/
    private static void helper(Integer level, String source, String msg) {
        if (shouldLog()) {

            String text = msg;
            String tag = source;

            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    logOut(level, PREFIX + tag, s1);
//                    Log.i(PREFIX + tag, s1);
                }
                logOut(level, PREFIX + tag, s);
//                Log.i(PREFIX + tag, s);
            } else {
                logOut(level, PREFIX + tag, text);
//                Log.i(PREFIX + tag, text);
            }
        }
    }

    /** Вывод ошибки, где:
     * - level - уровень (Error);
     * - source - класс источника;
     * - msg - содержание. */
    private static void logOut (Integer level, String source, String msg) {
        switch (level) {
            case Log.VERBOSE: Log.v(PREFIX + source, msg);
            case Log.DEBUG: Log.d(PREFIX + source, msg);
            case Log.INFO: Log.i(PREFIX + source, msg);
            case Log.WARN: Log.w(PREFIX + source, msg);
            case Log.ERROR: Log.e(PREFIX + source, msg);
        }
    }


}

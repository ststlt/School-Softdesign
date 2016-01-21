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

    /** Send an INFO log message */
    public static void i (String tag, String text){
        if (shouldLog()) {
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    Log.i(PREFIX + tag, s1);
                }
                Log.i(PREFIX + tag, s);
            } else {
                Log.i(PREFIX + tag, text);
            }
        }
    }

    /** Send a DEBUG log message */
    public static void d (String tag, String text){
        if (shouldLog()) {
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    Log.d(PREFIX + tag, s1);
                }
                Log.d(PREFIX + tag, s);
            } else {
                Log.d(PREFIX + tag, text);
            }
        }
    }

    /** Send a ERROR log message and log the exception */
    public static void e (String tag, String text) {
        if (shouldLog()) {
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    Log.e(PREFIX + tag, s1);
                }
                Log.e(PREFIX + tag, s);
            } else {
                Log.e(PREFIX + tag, text);
            }
        }
    }

    /** Send a WARN log message and log the exception */
    public static void w (String tag, String text) {
        if (shouldLog()) {
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    Log.w(PREFIX + tag, s1);
                }
                Log.w(PREFIX + tag, s);
            } else {
                Log.w(PREFIX + tag, text);
            }
        }
    }

    /** Send a VERBOSE log message */
    public static void v (String tag, String text) {
        if (shouldLog()) {
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    Log.v(PREFIX + tag, s1);
                }
                Log.v(PREFIX + tag, s);
            } else {
                Log.v(PREFIX + tag, text);
            }
        }
    }
}

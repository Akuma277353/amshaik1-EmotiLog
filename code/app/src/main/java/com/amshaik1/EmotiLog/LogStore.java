package com.amshaik1.EmotiLog;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/* A storage class for all emojis in, uses sharedpreferences to store data as persistance storage */

public class LogStore {
    private static final String PREFS = "EmojiLogPrefs";
    private static final String KEY_LOGS = "logs";

    public static void addLog(Context ctx, String emotion) { //saves an entry
        List<LogEntry> logs = getLogs(ctx);
        logs.add(new LogEntry(logs.size() + 1, emotion, System.currentTimeMillis()));
        saveLogs(ctx, logs);
    }

    public static List<LogEntry> getLogs(Context ctx) { // pulls all logs from sharedpreferences
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String json = sp.getString(KEY_LOGS, "[]");
        Type type = new TypeToken<List<LogEntry>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private static void saveLogs(Context ctx, List<LogEntry> logs) { // Saves logs to SharedPreferences
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_LOGS, new Gson().toJson(logs)).apply();
    }

    public static void clearLogs(Context ctx) { // Clears all logs
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().remove(KEY_LOGS).apply();
    }
}
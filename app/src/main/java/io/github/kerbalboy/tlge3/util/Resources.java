package io.github.kerbalboy.tlge3.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.activity.StartActivity;
import io.github.kerbalboy.tlge3.secret.Secrets;

public class Resources {
    private static Resources instance;
    private final AppCompatActivity activity;

    private Resources(StartActivity startActivity) {
        activity = startActivity;
    }

    public double getCircuits() {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        return getDouble(sharedPreferences, "circuits");
    }

    public void saveCircuits(double circuits) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        putDouble(sharedPreferences, "circuits", circuits);
    }

    public double get(String key) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        return getDouble(sharedPreferences, key);
    }

    public void save(String key, double value) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        putDouble(sharedPreferences, key, value);
    }

    public boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, false);
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);

        editor.apply();
    }

    private void putDouble(SharedPreferences sharedPreferences, String key, double value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(key, Double.doubleToRawLongBits(value));

        editor.apply();
    }

    private double getDouble(SharedPreferences sharedPreferences, String key) {
        return Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToLongBits(0)));
    }

    public void clearAll() {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isTroll = getBoolean("isTroll");

        editor.clear();
        editor.apply();

        saveBoolean("isTroll", isTroll);

        Secrets.getInstance().loadSecrets();
    }

    public void clearPrestige() {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("circuits");
        editor.remove("stmki");
        editor.remove("stmkii");
        editor.remove("stmkiii");
        editor.remove("chick");
        editor.remove("troll");
        editor.remove("j5087");

        editor.apply();
    }

    public static Resources getInstance() {
        return Resources.instance;
    }

    public static void setInstance(Resources instance) {
        Resources.instance = instance;
    }

    public static void createNewInstance(StartActivity activity) {
        if (Resources.instance == null) {
            Resources.setInstance(new Resources(activity));
        }
    }
}

package io.github.kerbalboy.tlge3.tick;

import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.util.Resources;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.activity.HomeActivity;
import io.github.kerbalboy.tlge3.activity.UpgradeActivity;

public class GameTick {
    final Resources resources;

    private static GameTick instance;

    public final Handler handler = new Handler();
    public final TickRunnable runnable = new TickRunnable(this);

    private GameTick() {
        resources = Resources.getInstance();

        handler.postDelayed(runnable, 1000);
    }

    public void tick(AppCompatActivity activity) {
        double circuits = resources.getCircuits() + getCircuitsPerSecond();
        resources.saveCircuits(circuits);

        if (activity == null) {
            return;
        }

        if (activity instanceof HomeActivity) {
            HomeActivity homeActivity = (HomeActivity) activity;

            homeActivity.setCircuitsText();
            homeActivity.setJCoinsTextView();
        }

        if (activity instanceof UpgradeActivity) {
            ((UpgradeActivity) activity).updateButtonVisibilities(circuits);
        }
    }

    public static GameTick getInstance() {
        return GameTick.instance;
    }

    public static void setInstance(GameTick instance) {
        GameTick.instance = instance;
    }

    public static void createNewInstance(AppCompatActivity activity) {
        if (GameTick.instance == null) {
            GameTick.setInstance(new GameTick());
            GameTick.getInstance().runnable.setActivity(activity);
        }
    }

    public double getCircuitsPerSecond() {
        double circuitsPerSecond = 0;

        circuitsPerSecond += resources.get("stmki");
        circuitsPerSecond += resources.get("stmkii") * 10D;
        circuitsPerSecond += resources.get("stmkiii") * 100D;
        circuitsPerSecond += resources.get("chick") * 10000000D;
        circuitsPerSecond += resources.get("troll") * 10000000000000000000D;
        circuitsPerSecond += resources.get("j5087") * 10000000000000000000000000D;

        circuitsPerSecond = Math.floor(circuitsPerSecond * (1 + (0.1 * resources.get("jcoins"))));

        circuitsPerSecond = circuitsPerSecond * Math.pow(2, Secrets.getInstance().getUnlocked());

        return circuitsPerSecond;
    }
}

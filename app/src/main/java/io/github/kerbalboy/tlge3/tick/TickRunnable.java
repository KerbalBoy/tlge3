package io.github.kerbalboy.tlge3.tick;

import androidx.appcompat.app.AppCompatActivity;

public class TickRunnable implements Runnable {
    private final GameTick gameTick;
    private AppCompatActivity activity;

    public TickRunnable(GameTick gameTick) {
        this.gameTick = gameTick;
    }

    public void run() {
        gameTick.tick(activity);
        gameTick.handler.postDelayed(this, 1000);
    }

    public void setActivity(AppCompatActivity a) {
        activity = a;
    }
}

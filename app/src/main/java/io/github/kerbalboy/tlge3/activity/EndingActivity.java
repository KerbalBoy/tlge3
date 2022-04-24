package io.github.kerbalboy.tlge3.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.dialogue.BadEndingDialogue;
import io.github.kerbalboy.tlge3.dialogue.GoodEndingDialogue;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.tick.GameTick;

public class EndingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        GameTick gameTick = GameTick.getInstance();
        gameTick.runnable.setActivity(this);

        Secrets.getInstance(this);

        Button goodEndingButton = findViewById(R.id.goodEndingButton);
        Button badEndingButton = findViewById(R.id.badEndingButton);

        goodEndingButton.setOnClickListener(view -> {
            GoodEndingDialogue dialogue = new GoodEndingDialogue(this, view);
            dialogue.next();
        });

        badEndingButton.setOnClickListener(view -> {
            BadEndingDialogue dialogue = new BadEndingDialogue(this, view);
            dialogue.next();
        });
    }
}
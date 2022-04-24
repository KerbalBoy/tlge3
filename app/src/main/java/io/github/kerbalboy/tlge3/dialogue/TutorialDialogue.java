package io.github.kerbalboy.tlge3.dialogue;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.activity.HomeActivity;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.util.Resources;

public class TutorialDialogue extends Dialogue {
    final  AppCompatActivity activity;

    public TutorialDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.tutorial_one, R.drawable._805j),
                new Message(activity, R.string.tutorial_two, R.drawable._805j),
                new Message(activity, R.string.tutorial_three, R.drawable._805j),
                new Message(activity, R.string.tutorial_four, R.drawable._805j),
                new Message(activity, R.string.tutorial_five, R.drawable._805j),
                new Message(activity, R.string.tutorial_six, R.drawable._805j),
                new Message(activity, R.string.tutorial_seven, R.drawable._805j)
        )));

        this.activity = activity;
    }

    @Override
    public void end() {
        Resources.getInstance().saveBoolean("tutorial", true);

        if (Resources.getInstance().getBoolean("isTroll")) {
            SecretDialogue dialogue = Secrets.getInstance().unlockWithLayeredDialogue("trollTutorialSecret", this.view);
            dialogue.setDialogue(this);
        } else {
            endCallback();
        }
    }

    @Override
    public void endCallback() {
        Intent switchActivityIntent = new Intent(this.activity, HomeActivity.class);
        this.activity.finish();
        this.activity.startActivity(switchActivityIntent);
    }
}

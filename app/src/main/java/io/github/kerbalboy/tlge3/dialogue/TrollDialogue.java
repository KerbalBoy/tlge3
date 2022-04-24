package io.github.kerbalboy.tlge3.dialogue;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.util.Resources;

public class TrollDialogue extends Dialogue {
    public TrollDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.troll_one, R.drawable.trololol),
                new Message(activity, R.string.troll_two, R.drawable._805j),
                new Message(activity, R.string.troll_three, R.drawable.trololol),
                new Message(activity, R.string.troll_four, R.drawable._805j),
                new Message(activity, R.string.troll_five, R.drawable._805j),
                new Message(activity, R.string.troll_six, R.drawable.trololol),
                new Message(activity, R.string.troll_seven, R.drawable.trololol),
                new Message(activity, R.string.troll_eight, R.drawable._805j),
                new Message(activity, R.string.troll_nine, R.drawable._805j)
        )));
    }

    @Override
    public void end() {
        if (Resources.getInstance().getBoolean("isTroll")) {
            Secrets.getInstance().unlock("trollProcessorSecret", this.view);
        }
    }
}

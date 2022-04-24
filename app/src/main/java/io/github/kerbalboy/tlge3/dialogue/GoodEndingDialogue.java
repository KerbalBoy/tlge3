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

public class GoodEndingDialogue extends Dialogue {
    public GoodEndingDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.good_ending_one, R.drawable._805j),
                new Message(activity, R.string.good_ending_two, R.drawable.j5087),
                new Message(activity, R.string.good_ending_three, R.drawable._805j),
                new Message(activity, R.string.good_ending_four, R.drawable.j5087),
                new Message(activity, R.string.good_ending_five, R.drawable.j5087),
                new Message(activity, R.string.good_ending_six, R.drawable.j5087),
                new Message(activity, R.string.good_ending_seven, R.drawable.j5087),
                new Message(activity, R.string.good_ending_eight, R.drawable._805j),
                new Message(activity, R.string.good_ending_nine, R.drawable.system),
                new Message(activity, R.string.good_ending_ten, R.drawable.j5087),
                new Message(activity, R.string.good_ending_eleven, R.drawable.j5087),
                new Message(activity, R.string.good_ending_twelve, R.drawable.j5087),
                new Message(activity, R.string.good_ending_thirteen, R.drawable.j5087),

                new Message(activity, R.string.yay, R.drawable.kevin),
                new Message(activity, R.string.yay, R.drawable.badass),
                new Message(activity, R.string.yay, R.drawable.blaustein),
                new Message(activity, R.string.yay, R.drawable.brunella),
                new Message(activity, R.string.yay, R.drawable.fari),
                new Message(activity, R.string.yay, R.drawable.flakenstein),
                new Message(activity, R.string.yay, R.drawable.jean_charles),
                new Message(activity, R.string.yay, R.drawable.jennifer),
                new Message(activity, R.string.yay, R.drawable.manfred),
                new Message(activity, R.string.yay, R.drawable.misterhey),
                new Message(activity, R.string.yay, R.drawable.nabila),
                new Message(activity, R.string.yay, R.drawable.rainbowcarl),
                new Message(activity, R.string.yay, R.drawable.sacha),
                new Message(activity, R.string.yay, R.drawable.sarah),
                new Message(activity, R.string.yay, R.drawable.saul),
                new Message(activity, R.string.yay, R.drawable.steve),
                new Message(activity, R.string.yay, R.drawable.tim),
                new Message(activity, R.string.yay, R.drawable.wendy),
                new Message(activity, R.string.yay, R.drawable.zwili),

                new Message(activity, R.string.good_ending_fourteen, R.drawable.j5087),
                new Message(activity, R.string.good_ending_fifteen, R.drawable.j5087),
                new Message(activity, R.string.good_ending_sixteen, R.drawable.system)
        )));
    }

    @Override
    public void end() {
        if (Resources.getInstance().getBoolean("isTroll")) {
            SecretDialogue dialogue = Secrets.getInstance().unlockWithLayeredDialogue("trollEnding", this.view);
            dialogue.setDialogue(this);
        } else {
            SecretDialogue dialogue = Secrets.getInstance().unlockWithLayeredDialogue("goodEnding", this.view);
            dialogue.setDialogue(this);
        }
    }

    @Override
    public void endCallback() {
        Intent switchActivityIntent = new Intent(this.getActivity(), HomeActivity.class);
        this.getActivity().finish();
        this.getActivity().startActivity(switchActivityIntent);
    }
}

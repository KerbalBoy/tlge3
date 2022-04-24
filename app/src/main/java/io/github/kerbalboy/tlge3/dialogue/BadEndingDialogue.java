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

public class BadEndingDialogue extends Dialogue {
    public BadEndingDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.bad_ending_one, R.drawable.j5087),
                new Message(activity, R.string.bad_ending_two, R.drawable._805j),
                new Message(activity, R.string.bad_ending_three, R.drawable._805j),
                new Message(activity, R.string.bad_ending_four, R.drawable.j5087),
                new Message(activity, R.string.bad_ending_five, R.drawable._805j),
                new Message(activity, R.string.bad_ending_six, R.drawable._805j),
                new Message(activity, R.string.bad_ending_seven, R.drawable._805j),
                new Message(activity, R.string.bad_ending_eight, R.drawable.j5087),
                new Message(activity, R.string.bad_ending_nine, R.drawable.system),
                new Message(activity, R.string.bad_ending_ten, R.drawable._805j),
                new Message(activity, R.string.bad_ending_eleven, R.drawable._805j),
                new Message(activity, R.string.bad_ending_twelve, R.drawable._805j),
                new Message(activity, R.string.bad_ending_thirteen, R.drawable._805j),
                new Message(activity, R.string.bad_ending_fourteen, R.drawable._805j),
                new Message(activity, R.string.bad_ending_fifteen, R.drawable._805j),

                new Message(activity, R.string.no, R.drawable.kevin),
                new Message(activity, R.string.no, R.drawable.badass),
                new Message(activity, R.string.no, R.drawable.blaustein),
                new Message(activity, R.string.no, R.drawable.brunella),
                new Message(activity, R.string.no, R.drawable.fari),
                new Message(activity, R.string.no, R.drawable.flakenstein),
                new Message(activity, R.string.no, R.drawable.jean_charles),
                new Message(activity, R.string.no, R.drawable.jennifer),
                new Message(activity, R.string.no, R.drawable.manfred),
                new Message(activity, R.string.no, R.drawable.misterhey),
                new Message(activity, R.string.no, R.drawable.nabila),
                new Message(activity, R.string.no, R.drawable.rainbowcarl),
                new Message(activity, R.string.no, R.drawable.sacha),
                new Message(activity, R.string.no, R.drawable.sarah),
                new Message(activity, R.string.no, R.drawable.saul),
                new Message(activity, R.string.no, R.drawable.steve),
                new Message(activity, R.string.no, R.drawable.tim),
                new Message(activity, R.string.no, R.drawable.wendy),
                new Message(activity, R.string.no, R.drawable.zwili),

                new Message(activity, R.string.bad_ending_sixteen, R.drawable._805j),
                new Message(activity, R.string.bad_ending_seventeen, R.drawable._805j),
                new Message(activity, R.string.bad_ending_eighteen, R.drawable._805j),
                new Message(activity, R.string.bad_ending_nineteen, R.drawable._805j),
                new Message(activity, R.string.bad_ending_twenty, R.drawable._805j)
        )));
    }

    @Override
    public void end() {
        if (Resources.getInstance().getBoolean("isTroll")) {
            SecretDialogue dialogue = Secrets.getInstance().unlockWithLayeredDialogue("trollEnding", this.view);
            dialogue.setDialogue(this);
        } else {
            SecretDialogue dialogue = Secrets.getInstance().unlockWithLayeredDialogue("badEnding", this.view);
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

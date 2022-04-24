package io.github.kerbalboy.tlge3.dialogue;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.activity.EndingActivity;

public class j5087Dialogue extends Dialogue {
    public j5087Dialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.j5087_one, R.drawable.j5087),
                new Message(activity, R.string.j5087_two, R.drawable._805j),
                new Message(activity, R.string.j5087_three, R.drawable.j5087),
                new Message(activity, R.string.j5087_four, R.drawable.j5087),
                new Message(activity, R.string.j5087_five, R.drawable.j5087),
                new Message(activity, R.string.j5087_six, R.drawable._805j)
        )));
    }

    @Override
    public void end() {
        Intent switchActivityIntent = new Intent(this.getActivity(), EndingActivity.class);
        this.getActivity().finish();
        this.getActivity().startActivity(switchActivityIntent);
    }
}

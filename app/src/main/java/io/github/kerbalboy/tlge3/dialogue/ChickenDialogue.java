package io.github.kerbalboy.tlge3.dialogue;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.kerbalboy.tlge3.R;

public class ChickenDialogue extends Dialogue {
    public ChickenDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.chicken_one, R.drawable.ginette),
                new Message(activity, R.string.chicken_two, R.drawable.kevin),
                new Message(activity, R.string.chicken_three, R.drawable._805j),
                new Message(activity, R.string.chicken_four, R.drawable._805j),
                new Message(activity, R.string.chicken_five, R.drawable._805j),
                new Message(activity, R.string.chicken_six, R.drawable.system),
                new Message(activity, R.string.chicken_seven, R.drawable._805j),
                new Message(activity, R.string.chicken_eight, R.drawable._805j),
                new Message(activity, R.string.chicken_nine, R.drawable._805j),
                new Message(activity, R.string.chicken_ten, R.drawable.system),
                new Message(activity, R.string.chicken_eleven, R.drawable.system),
                new Message(activity, R.string.chicken_twelve, R.drawable._805j)
        )));
    }
}

package io.github.kerbalboy.tlge3.dialogue;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.kerbalboy.tlge3.R;

public class SwissTrustOneDialogue extends Dialogue {
    public SwissTrustOneDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.st_one_one, R.drawable.swisstrust),
                new Message(activity, R.string.st_one_two, R.drawable._805j)
        )));
    }
}

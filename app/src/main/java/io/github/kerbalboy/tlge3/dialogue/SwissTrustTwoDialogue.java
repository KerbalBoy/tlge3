package io.github.kerbalboy.tlge3.dialogue;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.kerbalboy.tlge3.R;

public class SwissTrustTwoDialogue extends Dialogue {
    public SwissTrustTwoDialogue(AppCompatActivity activity, View view) {
        super(view, new ArrayList<>(Arrays.asList(
                new Message(activity, R.string.st_two_one, R.drawable.swisstrust),
                new Message(activity, R.string.st_two_two, R.drawable._805j)
        )));
    }
}

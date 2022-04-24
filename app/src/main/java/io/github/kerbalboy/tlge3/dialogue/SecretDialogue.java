package io.github.kerbalboy.tlge3.dialogue;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.activity.UpgradeActivity;

public class SecretDialogue extends Dialogue {
    private Dialogue dialogue;
    private UpgradeActivity activity;

    public SecretDialogue(AppCompatActivity activity, View view, int text) {
        super(view, new ArrayList<>(Collections.singletonList(
                new Message(activity, text, R.drawable.system)
        )));
    }

    @Override
    public void end() {
        if (this.dialogue != null) {
            this.dialogue.endCallback();
        }

        if (this.activity != null) {
            this.activity.prestigeCallback();
        }
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    public void setActivity(UpgradeActivity activity) {
        this.activity = activity;
    }
}

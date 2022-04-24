package io.github.kerbalboy.tlge3.secret;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.dialogue.SecretDialogue;
import io.github.kerbalboy.tlge3.util.Resources;

class Secret {
    public final String saveName;
    public final int text;
    public boolean isUnlocked;

    public Secret(String saveName, int text) {
        this.saveName = saveName;
        this.text = text;
        this.isUnlocked = Resources.getInstance().getBoolean(this.saveName);
    }

    public SecretDialogue unlock(AppCompatActivity activity, View view) {
        this.isUnlocked = true;

        Resources.getInstance().saveBoolean(this.saveName, true);

        SecretDialogue dialogue = new SecretDialogue(activity, view, this.text);
        dialogue.next();

        return dialogue;
    }
}

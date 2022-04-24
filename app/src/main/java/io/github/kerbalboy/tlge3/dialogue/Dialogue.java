package io.github.kerbalboy.tlge3.dialogue;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Dialogue {
    protected final View view;

    protected int messagePosition;
    protected final ArrayList<Message> messages;

    protected Views views;

    public Dialogue(View view, ArrayList<Message> messages) {
        this.view = view;
        this.messages = messages;

        this.messagePosition = 0;

        for (Message message: this.messages) {
            message.setDialogue(this);
        }
    }

    public void next() {
        int size = this.messages.size();

        if (this.messagePosition == 0) {
            this.views = this.messages.get(this.messagePosition).start(this.view);
            this.messagePosition++;
            return;
        }

        if (this.messagePosition >= size) {
            this.messages.get(size - 1).end(this.views);

            this.end();

            return;
        }

        this.messages.get(this.messagePosition).show(this.views);
        this.messagePosition++;
    }

    public void end() {
        endCallback();
    }

    public void endCallback() {

    }

    public View getView() {
        return this.view;
    }

    public AppCompatActivity getActivity() {
        return this.messages.get(0).currentActivity;
    }
}

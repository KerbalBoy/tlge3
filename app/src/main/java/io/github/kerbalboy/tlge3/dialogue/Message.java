package io.github.kerbalboy.tlge3.dialogue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.R;

public class Message {
    private Dialogue dialogue;
    protected final AppCompatActivity currentActivity;
    private final String text;
    private final int iconId;

    public Message(AppCompatActivity current_activity, int textId, int iconId) {
        this.dialogue = null;
        this.currentActivity = current_activity;
        this.text = current_activity.getString(textId);
        this.iconId = iconId;
    }

    public void show(Views views) {
        views.getTextView().setText(this.text);
        views.getIconView().setImageResource(this.iconId);
    }

    @SuppressLint({"ClickableViewAccessibility", "ObsoleteSdkInt", "InflateParams"})
    public Views start(View view) {
        LayoutInflater inflater = (LayoutInflater)
                this.currentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dialogue_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0xAA000000));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnClickListener(x -> this.dialogue.next());

        TextView textView = popupWindow.getContentView().findViewById(R.id.dialogueText);
        ImageView iconView = popupWindow.getContentView().findViewById(R.id.iconImage);
        Views views = new Views(popupWindow, textView, iconView);

        this.show(views);

        return views;
    }

    public void end(Views views) {
        views.getPopupWindow().dismiss();
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }
}

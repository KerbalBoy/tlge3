package io.github.kerbalboy.tlge3.dialogue;

import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Views {
    private final PopupWindow popupWindow;
    private final TextView textView;
    private final ImageView iconView;

    public Views(PopupWindow popupWindow, TextView textView, ImageView iconView) {
        this.popupWindow = popupWindow;
        this.textView = textView;
        this.iconView = iconView;
    }

    public PopupWindow getPopupWindow() {
        return this.popupWindow;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public ImageView getIconView() {
        return this.iconView;
    }
}

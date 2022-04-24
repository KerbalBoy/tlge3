package io.github.kerbalboy.tlge3.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.dialogue.TutorialDialogue;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.tick.GameTick;
import io.github.kerbalboy.tlge3.util.Resources;

public class StartActivity extends AppCompatActivity {
    Button startButton;

    ImageButton frenchButton;
    ImageButton englishButton;
    ImageButton trolololButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Resources.createNewInstance(this);
        GameTick.createNewInstance(this);
        Secrets.createNewInstance(this);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(this::play);
    }

    private void play(View view) {
        if (Resources.getInstance().getBoolean("tutorial")) {
            Intent switchActivityIntent = new Intent(this, HomeActivity.class);
            this.finish();
            this.startActivity(switchActivityIntent);
        } else if (Resources.getInstance().getBoolean("language")) {
            new TutorialDialogue(this, startButton).next();
        } else if (Resources.getInstance().getBoolean("isTroll")) {
            new TutorialDialogue(this, startButton).next();
        } else {
            selectLanguage(view);
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "ObsoleteSdkInt", "InflateParams"})
    public void selectLanguage(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.language_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0xAA000000));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        frenchButton = popupWindow.getContentView().findViewById(R.id.frenchButton);
        englishButton = popupWindow.getContentView().findViewById(R.id.englishButton);
        trolololButton = popupWindow.getContentView().findViewById(R.id.trolololButton);

        frenchButton.setOnClickListener(x -> setLocale("fr"));

        englishButton.setOnClickListener(x -> setLocale("en"));

        trolololButton.setVisibility(View.GONE);
    }

    private void setLocale(String lang) {
        Resources.getInstance().saveBoolean("language", true);

        Locale myLocale = new Locale(lang);
        android.content.res.Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, StartActivity.class);
        finish();
        startActivity(refresh);
    }
}

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import io.github.kerbalboy.tlge3.tick.GameTick;
import io.github.kerbalboy.tlge3.util.NavigationBar;
import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.util.Resources;

public class SettingsActivity extends AppCompatActivity {
    GameTick gameTick;

    Button languageButton;

    ImageButton frenchButton;
    ImageButton englishButton;
    ImageButton trolololButton;

    Button restartButton;
    private int timesRestartButtonClicked;

    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        new NavigationBar(this);
        gameTick = GameTick.getInstance();

        gameTick.runnable.setActivity(this);

        languageButton = findViewById(R.id.goodEndingButton);
        languageButton.setOnClickListener(this::onButtonShowPopupWindowClick);

        restartButton = findViewById(R.id.badEndingButton);
        timesRestartButtonClicked = 0;

        restartButton.setOnClickListener(x -> {
            if (timesRestartButtonClicked >= 5) {
                Resources.getInstance().clearAll();

                Intent switchActivityIntent = new Intent(this, StartActivity.class);
                finish();
                this.startActivity(switchActivityIntent);
            }

            timesRestartButtonClicked++;

            Toast.makeText(this, Integer.toString(6 - timesRestartButtonClicked), Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint({"ClickableViewAccessibility", "ObsoleteSdkInt", "InflateParams"})
    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.language_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0xAA000000));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });

        frenchButton = popupWindow.getContentView().findViewById(R.id.frenchButton);
        englishButton = popupWindow.getContentView().findViewById(R.id.englishButton);
        trolololButton = popupWindow.getContentView().findViewById(R.id.trolololButton);

        frenchButton.setOnClickListener(x -> setLocale("fr", false));

        englishButton.setOnClickListener(x -> setLocale("en", false));

        trolololButton.setOnClickListener(x -> setLocale("yo", true));
    }

    private void setLocale(String lang, boolean isTroll) {
        Resources.getInstance().saveBoolean("isTroll", isTroll);

        popupWindow.dismiss();

        Locale myLocale = new Locale(lang);
        android.content.res.Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, HomeActivity.class);
        finish();
        startActivity(refresh);
    }
}
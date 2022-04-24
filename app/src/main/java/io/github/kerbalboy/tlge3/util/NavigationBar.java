package io.github.kerbalboy.tlge3.util;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.activity.HomeActivity;
import io.github.kerbalboy.tlge3.activity.SettingsActivity;
import io.github.kerbalboy.tlge3.activity.UpgradeActivity;

public class NavigationBar {
    final AppCompatActivity activity;

    final ImageButton mainButton;
    final ImageButton upgradeButton;
    final ImageButton settingsButton;

    final ImageView barView;
    
    public NavigationBar(AppCompatActivity activity) {
        this.activity = activity;

        this.mainButton = activity.findViewById(R.id.mainButton);
        this.upgradeButton = activity.findViewById(R.id.upgradeButton);
        this.settingsButton = activity.findViewById(R.id.settingsButton);
        this.barView = activity.findViewById(R.id.barView);

        int scaleFactor = 2500 / Resources.getSystem().getDisplayMetrics().widthPixels;

        this.configureSize(this.mainButton, scaleFactor);
        this.configureSize(this.upgradeButton, scaleFactor);
        this.configureSize(this.settingsButton, scaleFactor);

        ViewGroup.LayoutParams params = this.barView.getLayoutParams();
        params.height = 380 / scaleFactor;
        this.barView.setLayoutParams(params);

        if (!(this.activity instanceof HomeActivity)) {
            this.setTargetActivity(this.mainButton, HomeActivity.class);
        }

        if (!(this.activity instanceof UpgradeActivity)) {
            this.setTargetActivity(this.upgradeButton, UpgradeActivity.class);
        }

        if (!(this.activity instanceof SettingsActivity)) {
            this.setTargetActivity(this.settingsButton, SettingsActivity.class);
        }
    }

    private void configureSize(View view, int scaleFactor) {
        int height = 353 / scaleFactor;
        int width = 412 / scaleFactor;

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        params.width = width;
        view.setLayoutParams(params);
    }

    private void setTargetActivity(ImageButton button, Class<? extends AppCompatActivity> clazz) {
        button.setOnClickListener(view -> switchActivities(clazz));
    }

    private void switchActivities(Class<? extends AppCompatActivity> clazz) {
        Intent switchActivityIntent = new Intent(this.activity, clazz);
        activity.finish();
        this.activity.startActivity(switchActivityIntent);
    }
}

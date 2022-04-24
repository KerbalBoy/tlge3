package io.github.kerbalboy.tlge3.activity;

import static io.github.kerbalboy.tlge3.R.id;
import static io.github.kerbalboy.tlge3.R.layout;
import static io.github.kerbalboy.tlge3.R.string;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.dialogue.ChickenDialogue;
import io.github.kerbalboy.tlge3.dialogue.SecretDialogue;
import io.github.kerbalboy.tlge3.dialogue.SwissTrustOneDialogue;
import io.github.kerbalboy.tlge3.dialogue.SwissTrustThreeDialogue;
import io.github.kerbalboy.tlge3.dialogue.SwissTrustTwoDialogue;
import io.github.kerbalboy.tlge3.dialogue.TrollDialogue;
import io.github.kerbalboy.tlge3.dialogue.j5087Dialogue;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.tick.GameTick;
import io.github.kerbalboy.tlge3.util.NavigationBar;
import io.github.kerbalboy.tlge3.util.Prestige;
import io.github.kerbalboy.tlge3.util.Resources;
import io.github.kerbalboy.tlge3.util.UnitLegend;

public class UpgradeActivity extends AppCompatActivity {
    Resources resources;
    GameTick gameTick;

    UpgradeButton swissTrustMarkOneButton;
    UpgradeButton swissTrustMarkTwoButton;
    UpgradeButton swissTrustMarkThreeButton;
    UpgradeButton chickenPowerButton;
    UpgradeButton trollProcessorButton;
    UpgradeButton j5087Button;
    UpgradeButton prestigeButton;

    UpgradeButton[] buttons;

    TextView prestigeBoostText;

    Button acceptPrestigeButton;
    Button denyPrestigeButton;

    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_upgrade);

        resources = Resources.getInstance();
        new NavigationBar(this);
        gameTick = GameTick.getInstance();
        Secrets.getInstance(this);

        gameTick.runnable.setActivity(this);

        swissTrustMarkOneButton = new UpgradeButton(new SwissTrustOneDialogue(this, findViewById(id.swissTrustMarkOneButton)), "stmki", string.swisstrust_processor_one, 10D);
        swissTrustMarkTwoButton = new UpgradeButton(new SwissTrustTwoDialogue(this, findViewById(id.swissTrustMarkTwoButton)), "stmkii", string.swisstrust_processor_two, 1000D);
        swissTrustMarkThreeButton = new UpgradeButton(new SwissTrustThreeDialogue(this, findViewById(id.swissTrustMarkThreeButton)), "stmkiii", string.swisstrust_processor_three, 10000D);
        chickenPowerButton = new UpgradeButton(new ChickenDialogue(this, findViewById(id.chickenPowerButton)), "chick", string.chicken_processor, 1000000000D);
        trollProcessorButton = new UpgradeButton(new TrollDialogue(this, findViewById(id.trollProcessorButton)), "troll", string.troll_processor, 1000000000000000000D);

        j5087Button = new UpgradeButton(new j5087Dialogue(this, findViewById(id.j5087Button)), "j5087", string.j5087, 100000000000000000000000000000000000000000000000000000000000D);
        prestigeButton = new UpgradeButton(new SwissTrustOneDialogue(this, findViewById(id.prestigeButton)), "prestige", string.prestige, 100000D);

        buttons = new UpgradeButton[]{swissTrustMarkOneButton, swissTrustMarkTwoButton, swissTrustMarkThreeButton, chickenPowerButton, trollProcessorButton, j5087Button, prestigeButton};

        this.updateButtonVisibilities(resources.getCircuits());

        prestigeButton.button.setOnClickListener(view -> onButtonShowPopupWindowClick(view, prestigeButton));
    }

    public void updateButtonVisibilities(double circuits) {
        for (UpgradeButton button: this.buttons) {
            button.updateVisibility(circuits);
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "ObsoleteSdkInt", "InflateParams"})
    public void onButtonShowPopupWindowClick(View view, UpgradeButton prestigeButton) {
        if (!prestigeButton.canBuy(Resources.getInstance().getCircuits())) {
            return;
        }

        Prestige prestige = new Prestige();
        double jCoins = prestige.getRewards();

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(layout.prestige_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0xAA000000));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });

        prestigeBoostText = popupWindow.getContentView().findViewById(id.prestigeBoostText);

        String s = getString(string.prestige_boost_one) + " " + (UnitLegend.convertToLegend(jCoins * 10)) + getString(string.prestige_boost_two);

        prestigeBoostText.setText(s);

        acceptPrestigeButton = popupWindow.getContentView().findViewById(id.acceptPrestigeButton);
        denyPrestigeButton = popupWindow.getContentView().findViewById(id.denyPrestigeButton);

        acceptPrestigeButton.setOnClickListener(x ->  {
            prestige.prestige();

            if (!Secrets.getInstance(this).isUnlocked("firstPrestigeSecret")) {
                SecretDialogue dialogue = Secrets.getInstance(this).unlockWithLayeredDialogue("firstPrestigeSecret", prestigeButton.button);
                dialogue.setActivity(this);
            } else {
                prestigeCallback();
            }
        });

        denyPrestigeButton.setOnClickListener(x -> popupWindow.dismiss());
    }

    public void prestigeCallback() {
        popupWindow.dismiss();

        Intent switchActivityIntent = new Intent(this, HomeActivity.class);
        this.finish();
        this.startActivity(switchActivityIntent);
    }
}
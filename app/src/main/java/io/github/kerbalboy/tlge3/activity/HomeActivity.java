package io.github.kerbalboy.tlge3.activity;

import static io.github.kerbalboy.tlge3.R.id;
import static io.github.kerbalboy.tlge3.R.layout;
import static io.github.kerbalboy.tlge3.R.string;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.tick.GameTick;
import io.github.kerbalboy.tlge3.util.NavigationBar;
import io.github.kerbalboy.tlge3.util.Resources;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.util.UnitLegend;

public class HomeActivity extends AppCompatActivity {
    private double circuits = 0;
    Resources resources;
    GameTick gameTick;

    Button circuitsButton;
    TextView circuitsTextView;

    TextView jCoinsTextView;

    TextView secretsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        new NavigationBar(this);
        resources = Resources.getInstance();
        gameTick = GameTick.getInstance();
        gameTick.runnable.setActivity(this);

        circuits = resources.getCircuits();

        circuitsButton = findViewById(id.circuitsButton);
        circuitsTextView = findViewById(id.circuitsTextView);

        jCoinsTextView = findViewById(id.jCoinsTextView);

        secretsTextView = findViewById(id.secretsTextView);

        circuitsButton.setOnClickListener(view -> {
            double newCircuits = 1 + (0.1 * resources.get("jcoins"));
            newCircuits = Math.floor(newCircuits * Math.pow(2, Secrets.getInstance().getUnlocked()));

            circuits = resources.getCircuits();
            resources.saveCircuits(circuits + newCircuits);

            setCircuitsText();
        });

        setCircuitsText();
        setJCoinsTextView();
        setSecretsTextView();
    }

    public void setCircuitsText() {
        double cps = gameTick.getCircuitsPerSecond();

        String s = UnitLegend.convertToLegend(resources.getCircuits()) + " " + getString(string.circuits) + " (" + UnitLegend.convertToLegend(cps) + "/s)";

        circuitsTextView.setText(s);
    }

    public void setJCoinsTextView() {
        double jCoins = resources.get("jcoins");

        if (jCoins == 0) {
            jCoinsTextView.setText("");
            jCoinsTextView.setVisibility(View.GONE);
            return;
        }

        String s = UnitLegend.convertToLegend(jCoins) + " " + getString(string.jcoins_one) + UnitLegend.convertToLegend(jCoins * 10L) + getString(string.jcoins_two);

        jCoinsTextView.setText(s);
    }

    public void setSecretsTextView() {
        int secrets = Secrets.getInstance().getUnlocked();

        if (secrets == 0) {
            secretsTextView.setText("");
            secretsTextView.setVisibility(View.GONE);
            return;
        }

        String s = UnitLegend.convertToLegend(secrets) + " " + getString(string.secrets_one) + "x" + UnitLegend.convertToLegend(Math.pow(2, secrets)) + " " + getString(string.secrets_two);

        secretsTextView.setText(s);
    }
}
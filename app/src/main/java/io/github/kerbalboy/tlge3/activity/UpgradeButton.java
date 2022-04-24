package io.github.kerbalboy.tlge3.activity;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.dialogue.Dialogue;
import io.github.kerbalboy.tlge3.secret.Secrets;
import io.github.kerbalboy.tlge3.util.Resources;
import io.github.kerbalboy.tlge3.util.UnitLegend;

public class UpgradeButton {
    protected final Button button;
    private final AppCompatActivity activity;

    private final Dialogue dialogue;

    private final String save_name;

    private final int text;

    private final double base_requirement;
    private double requirement;

    public UpgradeButton(Dialogue dialogue, String save_name, int text, double requirement) {
        Resources resources = Resources.getInstance();

        this.activity = dialogue.getActivity();
        this.button = (Button) dialogue.getView();

        this.dialogue = dialogue;

        this.save_name = save_name;

        this.text = text;

        this.base_requirement = requirement;
        this.requirement = Math.floor(this.base_requirement * Math.pow(1.12, this.getAmount()));

        this.button.setOnClickListener(view -> {
            double circuits = resources.getCircuits();

            if (this.canBuy(circuits)) {
                if (!this.save_name.equalsIgnoreCase("prestige") && this.getAmount() == 0) {
                    this.dialogue.next();
                }

                resources.saveCircuits(circuits - this.requirement);
                this.incrementAmount();
                this.updateVisibility(circuits - this.requirement);
            }
        });
    }

    public void updateVisibility(double circuits) {
        String amount = " x" + UnitLegend.convertToLegend(this.getAmount());
        String cost = " (" + UnitLegend.convertToLegend(this.requirement) + ")";
        String text;

        if (this.getAmount() > 0) {
            text = this.activity.getString(this.text) + amount + cost;
        } else if (this.canBuy(circuits)) {
            text = this.activity.getString(this.text) + cost;
        } else {
            text = "???" + cost;
        }

        if (this.canBuy(circuits)) {
            this.button.setTextColor(this.activity.getResources().getColor(R.color.text));
        } else {
            this.button.setTextColor(this.activity.getResources().getColor(R.color.text_dark));
        }

        this.button.setText(text);
    }

    public boolean canBuy(double circuits) {
        return circuits >= this.requirement;
    }

    private double getAmount() {
        return Resources.getInstance().get(this.save_name);
    }

    private void saveAmount(double upgrade) {
        Resources.getInstance().save(this.save_name, upgrade);
    }

    private void incrementAmount() {
        this.saveAmount(this.getAmount() + 1);

        this.requirement = Math.floor(this.base_requirement * Math.pow(1.12, this.getAmount()));

        if (this.getAmount() == 100) {
            switch (this.save_name) {
                case "stmki":
                    Secrets.getInstance().unlock("swissTrustMarkOne100", this.button);
                    break;
                case "stmkii":
                    Secrets.getInstance().unlock("swissTrustMarkTwo100", this.button);
                    break;
                case "stmkiii":
                    Secrets.getInstance().unlock("swissTrustMarkThree100", this.button);
                    break;
                case "chick":
                    Secrets.getInstance().unlock("chicken100", this.button);
                    break;
                case "troll":
                    Secrets.getInstance().unlock("troll100", this.button);
                    break;
                case "j5087":
                    Secrets.getInstance().unlock("j5087100", this.button);
                    break;
            }
        } else if (this.getAmount() == 1000) {
            switch (this.save_name) {
                case "stmki":
                    Secrets.getInstance().unlock("swissTrustMarkOne1k", this.button);
                    break;
                case "stmkii":
                    Secrets.getInstance().unlock("swissTrustMarkTwo1k", this.button);
                    break;
                case "stmkiii":
                    Secrets.getInstance().unlock("swissTrustMarkThree1k", this.button);
                    break;
                case "chick":
                    Secrets.getInstance().unlock("chicken1k", this.button);
                    break;
                case "troll":
                    Secrets.getInstance().unlock("troll1k", this.button);
                    break;
                case "j5087":
                    Secrets.getInstance().unlock("j50871k", this.button);
                    break;
            }
        }
    }
}

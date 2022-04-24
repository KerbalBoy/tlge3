package io.github.kerbalboy.tlge3.secret;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import io.github.kerbalboy.tlge3.R;
import io.github.kerbalboy.tlge3.dialogue.SecretDialogue;
import io.github.kerbalboy.tlge3.util.Resources;

public class Secrets {
    private static Secrets instance;
    private AppCompatActivity activity;

    private int unlocked;

    private final ArrayList<Secret> secrets = new ArrayList<>();

    private Secrets(AppCompatActivity activity) {
        this.activity = activity;

        Secret trollTutorialSecret = new Secret("trollTutorialSecret", R.string.troll_tutorial_secret);
        Secret trollProcessorSecret = new Secret("trollProcessorSecret", R.string.troll_processor_secret);

        Secret firstPrestigeSecret = new Secret("firstPrestigeSecret", R.string.first_prestige_secret);

        Secret goodEnding = new Secret("goodEnding", R.string.good_ending_secret);
        Secret badEnding = new Secret("badEnding", R.string.bad_ending_secret);
        Secret trollEnding = new Secret("trollEnding", R.string.troll_ending_secret);

        Secret swissTrustMarkOne100 = new Secret("swissTrustMarkOne100", R.string.st_one_100);
        Secret swissTrustMarkOne1k = new Secret("swissTrustMarkOne1k", R.string.st_one_1k);

        Secret swissTrustMarkTwo100 = new Secret("swissTrustMarkTwo100", R.string.st_two_100);
        Secret swissTrustMarkTwo1k = new Secret("swissTrustMarkTwo1k", R.string.st_two_1k);

        Secret swissTrustMarkThree100 = new Secret("swissTrustMarkThree100", R.string.st_three_100);
        Secret swissTrustMarkThree1k = new Secret("swissTrustMarkThree1k", R.string.st_three_1k);

        Secret chicken100 = new Secret("chicken100", R.string.chicken_100);
        Secret chicken1k = new Secret("chicken1k", R.string.chicken_1k);

        Secret troll100 = new Secret("troll100", R.string.troll_100);
        Secret troll1k = new Secret("troll1k", R.string.troll_1k);

        Secret j5087100 = new Secret("j5087100", R.string.j5087_100);
        Secret j50871k = new Secret("j50871k", R.string.j5087_1k);

        secrets.add(trollTutorialSecret);
        secrets.add(trollProcessorSecret);

        secrets.add(firstPrestigeSecret);

        secrets.add(goodEnding);
        secrets.add(badEnding);
        secrets.add(trollEnding);

        secrets.add(swissTrustMarkOne100);
        secrets.add(swissTrustMarkOne1k);

        secrets.add(swissTrustMarkTwo100);
        secrets.add(swissTrustMarkTwo1k);

        secrets.add(swissTrustMarkThree100);
        secrets.add(swissTrustMarkThree1k);

        secrets.add(chicken100);
        secrets.add(chicken1k);

        secrets.add(troll100);
        secrets.add(troll1k);

        secrets.add(j5087100);
        secrets.add(j50871k);

        for (Secret secret: secrets) {
            if (secret.isUnlocked) {
                unlocked++;
            }
        }
    }

    public SecretDialogue unlockWithLayeredDialogue(String name, View view) {
        Secret secret = getSecret(name);

        if (!secret.isUnlocked) {
            unlocked++;
        }

        return secret.unlock(this.activity, view);
    }

    public Secret getSecret(String name) {
        for (Secret secret: secrets) {
            if (secret.saveName.equalsIgnoreCase(name)) {
                return secret;
            }
        }

        return null;
    }

    public boolean isUnlocked(String name) {
        return getSecret(name) != null && getSecret(name).isUnlocked;
    }

    public void unlock(String name, View view) {
        Secret secret = getSecret(name);

        if (!secret.isUnlocked) {
            unlocked++;
            secret.unlock(this.activity, view);
        }
    }

    public int getUnlocked() {
        return unlocked;
    }

    public void loadSecrets() {
        Resources resources = Resources.getInstance();

        for (Secret secret: secrets) {
            resources.saveBoolean(secret.saveName, secret.isUnlocked);
        }
    }

    public static Secrets getInstance() {
        return Secrets.instance;
    }

    public static Secrets getInstance(AppCompatActivity activity) {
        Secrets instance = Secrets.instance;

        instance.activity = activity;

        return Secrets.getInstance();
    }

    public static void setInstance(Secrets instance) {
        Secrets.instance = instance;
    }

    public static void createNewInstance(AppCompatActivity activity) {
        if (Secrets.instance == null) {
            Secrets instance = new Secrets(activity);

            Secrets.setInstance(instance);
        }
    }
}

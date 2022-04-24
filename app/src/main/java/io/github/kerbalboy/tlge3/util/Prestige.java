package io.github.kerbalboy.tlge3.util;

public class Prestige {
    private final double circuits;

    public Prestige() {
        Resources resources = Resources.getInstance();

        this.circuits = resources.getCircuits();
    }

    public void prestige() {
        Resources resources = Resources.getInstance();

        double jCoins = getRewards();

        resources.clearPrestige();
        resources.save("jcoins", jCoins + resources.get("jcoins"));

        resources.save("prestiges", resources.get("prestiges") + 1);
    }

    public double getRewards() {
        double totalCircuits = this.circuits;

        double circuits = totalCircuits;
        double jCoins = 0;

        if (totalCircuits < 1000D) {
            jCoins += Math.floor(Math.pow(circuits, 1.0 / 5.0));
            return jCoins;
        }

        jCoins += Math.floor(Math.pow(1000D, 1.0 / 5.0));
        circuits -= 1000D;

        if (totalCircuits < 1000000D) {
            jCoins += Math.floor(Math.pow(circuits, 1.0 / 3.5));
            return jCoins;
        }

        jCoins += Math.floor(Math.pow(1000000D, 1.0 / 3.5));
        circuits -= 1000000D;

        if (totalCircuits < 1000000000000D) {
            jCoins += Math.floor(Math.pow(circuits, 1.0 / 3.0));
            return jCoins;
        }

        jCoins += Math.floor(Math.pow(1000000000000D, 1.0 / 3.0));
        circuits -= 1000000000000D;

        if (totalCircuits < 1000000000000000000000000000000000D) {
            jCoins += Math.floor(Math.pow(circuits, 1.0 / 2.4));
            return jCoins;
        }

        jCoins += Math.floor(Math.pow(1000000000000000000000000000000000D, 1.0 / 2.4));
        circuits -= 1000000000000000000000000000000000D;

        jCoins += Math.floor(Math.pow(circuits, 1.0 / 1.8));

        return jCoins;
    }
}

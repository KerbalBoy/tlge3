package io.github.kerbalboy.tlge3.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class UnitLegend {
    // 1000 ^ string name
    private static final String zero = "";
    private static final String one = "K";
    private static final String two = "M";
    private static final String three = "B";
    private static final String four = "t";
    private static final String five = "q";
    private static final String six = "Q";
    private static final String seven = "s";
    private static final String eight = "S";
    private static final String nine = "O";
    private static final String ten = "N";
    private static final String eleven = "D";
    private static final String twelve = "UD";
    private static final String thirteen = "DD";
    private static final String fourteen = "TD";
    private static final String fifteen = "qD";
    private static final String sixteen = "QD";
    private static final String seventeen = "sD";
    private static final String eighteen = "SD";
    private static final String nineteen = "OD";
    private static final String twenty = "ND";
    private static final String twentyOne = "V";
    private static final String twentyTwo = "UV";
    private static final String twentyThree = "DV";
    private static final String twentyFour = "TV";
    private static final String twentyFive = "qV";
    private static final String twentySix = "QV";
    private static final String twentySeven = "sV";
    private static final String twentyEight = "SV";
    private static final String twentyNine = "OV";
    private static final String thirty = "NV";
    private static final String thirtyOne = "T";
    private static final String thirtyTwo = "UT";
    private static final String thirtyThree = "DT";
    private static final String googol = "XX";

    public static String convertToLegend(double n) {
        n = Math.floor(n);

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.FLOOR);

        int power = 0;
        double number = n;

        while (number >= 1000) {
            number /= 1000;
            power++;
        }

        String suffix;

        switch (power) {
            case 0:
                suffix = zero;
                break;
            case 1:
                suffix = one;
                break;
            case 2:
                suffix = two;
                break;
            case 3:
                suffix = three;
                break;
            case 4:
                suffix = four;
                break;
            case 5:
                suffix = five;
                break;
            case 6:
                suffix = six;
                break;
            case 7:
                suffix = seven;
                break;
            case 8:
                suffix = eight;
                break;
            case 9:
                suffix = nine;
                break;
            case 10:
                suffix = ten;
                break;
            case 11:
                suffix = eleven;
                break;
            case 12:
                suffix = twelve;
                break;
            case 13:
                suffix = thirteen;
                break;
            case 14:
                suffix = fourteen;
                break;
            case 15:
                suffix = fifteen;
                break;
            case 16:
                suffix = sixteen;
                break;
            case 17:
                suffix = seventeen;
                break;
            case 18:
                suffix = eighteen;
                break;
            case 19:
                suffix = nineteen;
                break;
            case 20:
                suffix = twenty;
                break;
            case 21:
                suffix = twentyOne;
                break;
            case 22:
                suffix = twentyTwo;
                break;
            case 23:
                suffix = twentyThree;
                break;
            case 24:
                suffix = twentyFour;
                break;
            case 25:
                suffix = twentyFive;
                break;
            case 26:
                suffix = twentySix;
                break;
            case 27:
                suffix = twentySeven;
                break;
            case 28:
                suffix = twentyEight;
                break;
            case 29:
                suffix = twentyNine;
                break;
            case 30:
                suffix = thirty;
                break;
            case 31:
                suffix = thirtyOne;
                break;
            case 32:
                suffix = thirtyTwo;
                break;
            case 33:
                if (number >= 10) {
                    suffix = googol;
                    break;
                }
                suffix = thirtyThree;
                break;
            default:
                suffix = googol;
                break;
        }

        String num = df.format(number);

        if (num.equalsIgnoreCase("99.999")) {
            num = "100";
        }

        return num + suffix;
    }
}

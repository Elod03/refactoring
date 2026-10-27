package fr.efrei.transfret;

public class Montants {

    private static final double CENTIMES_PAR_EURO = 100.0;

    public static double arrondir(double valeur) {
        return Math.round(valeur * CENTIMES_PAR_EURO) / CENTIMES_PAR_EURO;
    }
}

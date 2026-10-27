package fr.efrei.transfret;

import java.util.Random;

public class GenerateurIdExpedition {

    private static final int SUFFIXE_MIN = 1000;
    private static final int SUFFIXE_PLAGE = 9000;

    private final Random aleatoire = new Random();
    private int compteur = 0;

    public String suivant() {
        compteur++;
        return "EXP-" + compteur + "-" + (SUFFIXE_MIN + aleatoire.nextInt(SUFFIXE_PLAGE));
    }
}

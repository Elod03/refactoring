package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private static final double COEF_FRAGILE = 1.15;
    private static final double COEF_DANGEREUX = 1.30;
    private static final double COEF_REFRIGERE = 1.20;
    private static final double COEF_URGENCE = 1.5;

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double MINIMUM_ASSURANCE = 15;

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        double poids = 0;
        double volume = 0;
        double valeur = 0;
        for (Colis c : colis) {
            poids = poids + c.getPoids();
            volume = volume + c.getVolume();
            valeur = valeur + c.getValeurDeclaree();
        }

        double prix = mode.getTarif().calculer(distance, poids, volume);

        if (TypeMarchandise.FRAGILE.estPresentDans(colis)) prix = prix * COEF_FRAGILE;
        if (TypeMarchandise.DANGEREUX.estPresentDans(colis)) prix = prix * COEF_DANGEREUX;
        if (TypeMarchandise.REFRIGERE.estPresentDans(colis)) prix = prix * COEF_REFRIGERE;
        if (urgent) prix = prix * COEF_URGENCE;

        prix = typeClient.getPolitiqueRemise().appliquer(prix);

        if (assurance) {
            prix = prix + Math.max(valeur * TAUX_ASSURANCE, MINIMUM_ASSURANCE);
        }
        return Montants.arrondir(prix);
    }
}

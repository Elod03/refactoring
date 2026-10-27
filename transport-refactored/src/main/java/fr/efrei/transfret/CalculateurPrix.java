package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private static final double TARIF_KM_ROUTE = 1.2;
    private static final double TARIF_KG_ROUTE = 0.10;
    private static final double TARIF_KM_RAIL = 0.8;
    private static final double TARIF_KG_RAIL = 0.07;
    private static final double MINIMUM_RAIL = 150;
    private static final double TARIF_KM_MER = 0.5;
    private static final double TARIF_KG_MER = 0.03;
    private static final double MINIMUM_MER = 500;
    private static final double TARIF_KM_AIR = 0.6;
    private static final double TARIF_KG_TAXABLE_AIR = 4.5;
    private static final double KG_PAR_M3_VOLUMETRIQUE = 167;

    private static final double COEF_FRAGILE = 1.15;
    private static final double COEF_DANGEREUX = 1.30;
    private static final double COEF_REFRIGERE = 1.20;
    private static final double COEF_URGENCE = 1.5;

    private static final double SEUIL_REMISE_ENTREPRISE_MAJOREE = 5000;
    private static final double COEF_REMISE_ENTREPRISE_MAJOREE = 0.85;
    private static final double COEF_REMISE_ENTREPRISE = 0.90;
    private static final double COEF_REMISE_PREMIUM = 0.80;

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double MINIMUM_ASSURANCE = 15;

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        double poids = 0;
        double volume = 0;
        double valeur = 0;
        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;
        for (Colis c : colis) {
            poids = poids + c.getPoids();
            volume = volume + c.getVolume();
            valeur = valeur + c.getValeurDeclaree();
            if (c.getType() == TypeMarchandise.FRAGILE) fragile = true;
            if (c.getType() == TypeMarchandise.DANGEREUX) dangereux = true;
            if (c.getType() == TypeMarchandise.REFRIGERE) refrigere = true;
        }

        double prix = switch (mode) {
            case ROUTE -> distance * TARIF_KM_ROUTE + poids * TARIF_KG_ROUTE;
            case RAIL -> Math.max(distance * TARIF_KM_RAIL + poids * TARIF_KG_RAIL, MINIMUM_RAIL);
            case MER -> Math.max(distance * TARIF_KM_MER + poids * TARIF_KG_MER, MINIMUM_MER);
            case AIR -> {
                double poidsTaxable = Math.max(poids, volume * KG_PAR_M3_VOLUMETRIQUE);
                yield distance * TARIF_KM_AIR + poidsTaxable * TARIF_KG_TAXABLE_AIR;
            }
        };

        if (fragile) prix = prix * COEF_FRAGILE;
        if (dangereux) prix = prix * COEF_DANGEREUX;
        if (refrigere) prix = prix * COEF_REFRIGERE;
        if (urgent) prix = prix * COEF_URGENCE;

        if (typeClient == TypeClient.ENTREPRISE) {
            if (prix > SEUIL_REMISE_ENTREPRISE_MAJOREE) {
                prix = prix * COEF_REMISE_ENTREPRISE_MAJOREE;
            } else {
                prix = prix * COEF_REMISE_ENTREPRISE;
            }
        } else if (typeClient == TypeClient.PREMIUM) {
            prix = prix * COEF_REMISE_PREMIUM;
        }

        if (assurance) {
            prix = prix + Math.max(valeur * TAUX_ASSURANCE, MINIMUM_ASSURANCE);
        }
        return Utils.arrondir(prix);
    }
}

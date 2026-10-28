package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private final Prix prix;

    public CalculateurPrix(Prix prix) {
        this.prix = prix;
    }

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        return Montants.arrondir(prix.calculer(new DemandePrix(colis, distance, mode, urgent, assurance, typeClient)));
    }
}

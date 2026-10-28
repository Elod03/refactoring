package fr.efrei.transfret;

import java.util.List;

public class Peniche extends Vehicule {

    public Peniche(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, ModeTransport.FLUVIAL);
    }

    @Override
    protected boolean accepte(List<Colis> colis) {
        return !TypeMarchandise.REFRIGERE.estPresentDans(colis);
    }

    @Override
    public void partir(int distance) {
        faireLePlein();
        System.out.println("La péniche " + immatriculation + " navigue sur " + distance + " km");
    }
}

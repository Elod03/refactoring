package fr.efrei.transfret;

import java.util.List;

public class Camion extends Vehicule {

    protected boolean adr;

    public Camion(String immatriculation, double capaciteMax, boolean adr) {
        super(immatriculation, capaciteMax, ModeTransport.ROUTE);
        this.adr = adr;
    }

    @Override
    protected boolean accepte(List<Colis> colis) {
        return !TypeMarchandise.REFRIGERE.estPresentDans(colis)
                && (adr || !TypeMarchandise.DANGEREUX.estPresentDans(colis));
    }

    @Override
    public void partir(int distance) {
        faireLePlein();
        System.out.println("Le camion " + immatriculation + " roule sur " + distance + " km");
    }
}

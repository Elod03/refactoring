package fr.efrei.transfret;

import java.util.List;

public class Avion extends Vehicule {

    private static final int ALTITUDE_CROISIERE_PIEDS = 35000;

    public Avion(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, ModeTransport.AIR);
    }

    @Override
    protected boolean accepte(List<Colis> colis) {
        return !TypeMarchandise.DANGEREUX.estPresentDans(colis) && !TypeMarchandise.REFRIGERE.estPresentDans(colis);
    }

    @Override
    public void partir(int distance) {
        faireLePlein();
        System.out.println("L'avion " + immatriculation + " roule sur la piste");
        System.out.println("L'avion " + immatriculation + " vole à " + ALTITUDE_CROISIERE_PIEDS + " pieds");
    }
}

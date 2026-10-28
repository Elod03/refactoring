package fr.efrei.transfret;

import java.util.List;

public class CamionFrigorifique extends Camion implements Refrigerable {

    public CamionFrigorifique(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, false);
    }

    @Override
    protected boolean accepte(List<Colis> colis) {
        return TypeMarchandise.REFRIGERE.estSeulDans(colis);
    }

    @Override
    public void partir(int distance) {
        super.partir(distance);
        activerRefrigeration(TEMPERATURE_REFRIGERATION);
    }

    @Override
    public void activerRefrigeration(double temperature) {
        System.out.println("Réfrigération activée à " + temperature + "°C sur " + immatriculation);
    }
}

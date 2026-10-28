package fr.efrei.transfret;

public class TarifFluvial implements TarifTransport {

    private static final double TARIF_KM = 0.40;
    private static final double TARIF_KG = 0.02;
    private static final double MINIMUM = 300;

    @Override
    public double calculer(int distance, double poids, double volume) {
        return Math.max(distance * TARIF_KM + poids * TARIF_KG, MINIMUM);
    }
}

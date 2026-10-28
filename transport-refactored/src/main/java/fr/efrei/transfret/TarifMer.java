package fr.efrei.transfret;

public class TarifMer implements TarifTransport {

    private static final double TARIF_KM = 0.5;
    private static final double TARIF_KG = 0.03;
    private static final double MINIMUM = 500;

    @Override
    public double calculer(int distance, double poids, double volume) {
        return Math.max(distance * TARIF_KM + poids * TARIF_KG, MINIMUM);
    }
}

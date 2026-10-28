package fr.efrei.transfret;

public class TarifRoute implements TarifTransport {

    private static final double TARIF_KM = 1.2;
    private static final double TARIF_KG = 0.10;

    @Override
    public double calculer(int distance, double poids, double volume) {
        return distance * TARIF_KM + poids * TARIF_KG;
    }
}

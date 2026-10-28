package fr.efrei.transfret;

public class TarifAir implements TarifTransport {

    private static final double TARIF_KM = 0.6;
    private static final double TARIF_KG_TAXABLE = 4.5;
    private static final double KG_PAR_M3_VOLUMETRIQUE = 167;

    @Override
    public double calculer(int distance, double poids, double volume) {
        double poidsTaxable = Math.max(poids, volume * KG_PAR_M3_VOLUMETRIQUE);
        return distance * TARIF_KM + poidsTaxable * TARIF_KG_TAXABLE;
    }
}

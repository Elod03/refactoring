package fr.efrei.transfret;

public class DelaiRoute implements DelaiTransport {

    private static final double VITESSE_KMH = 70.0;
    private static final int HEURES_CONDUITE_PAR_JOUR = 9;
    private static final int HEURES_CONDUITE_PAR_JOUR_URGENT = 18;

    @Override
    public int estimer(int distance, boolean urgent) {
        double heures = distance / VITESSE_KMH;
        int heuresParJour = urgent ? HEURES_CONDUITE_PAR_JOUR_URGENT : HEURES_CONDUITE_PAR_JOUR;
        return (int) Math.ceil(heures / heuresParJour);
    }
}

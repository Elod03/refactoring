package fr.efrei.transfret;

public class DelaiAir implements DelaiTransport {

    private static final int SEUIL_LONG_COURRIER_KM = 3000;
    private static final int JOURS = 1;
    private static final int JOURS_LONG_COURRIER = 2;

    @Override
    public int estimer(int distance, boolean urgent) {
        if (!urgent && distance > SEUIL_LONG_COURRIER_KM) {
            return JOURS_LONG_COURRIER;
        }
        return JOURS;
    }
}

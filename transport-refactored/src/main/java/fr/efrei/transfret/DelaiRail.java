package fr.efrei.transfret;

public class DelaiRail implements DelaiTransport {

    private static final double KM_PAR_JOUR = 1440.0;
    private static final int JOURS_MANUTENTION = 1;

    @Override
    public int estimer(int distance, boolean urgent) {
        int jours = (int) Math.ceil(distance / KM_PAR_JOUR);
        return urgent ? jours : jours + JOURS_MANUTENTION;
    }
}

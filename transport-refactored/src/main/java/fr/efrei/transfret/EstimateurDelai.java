package fr.efrei.transfret;

public class EstimateurDelai {

    private static final double VITESSE_ROUTE_KMH = 70.0;
    private static final int HEURES_CONDUITE_PAR_JOUR = 9;
    private static final int HEURES_CONDUITE_PAR_JOUR_URGENT = 18;
    private static final double KM_PAR_JOUR_RAIL = 1440.0;
    private static final int JOURS_MANUTENTION_RAIL = 1;
    private static final double KM_PAR_JOUR_MER = 720.0;
    private static final int JOURS_MANUTENTION_MER = 2;
    private static final int SEUIL_LONG_COURRIER_KM = 3000;
    private static final int JOURS_AIR = 1;
    private static final int JOURS_AIR_LONG_COURRIER = 2;
    private static final int DELAI_MINIMUM_JOURS = 1;

    public int estimer(int distance, ModeTransport mode, boolean urgent) {
        int jours = switch (mode) {
            case ROUTE -> {
                double heures = distance / VITESSE_ROUTE_KMH;
                int heuresParJour = urgent ? HEURES_CONDUITE_PAR_JOUR_URGENT : HEURES_CONDUITE_PAR_JOUR;
                yield (int) Math.ceil(heures / heuresParJour);
            }
            case RAIL -> {
                int joursRail = (int) Math.ceil(distance / KM_PAR_JOUR_RAIL);
                yield urgent ? joursRail : joursRail + JOURS_MANUTENTION_RAIL;
            }
            case MER -> (int) Math.ceil(distance / KM_PAR_JOUR_MER) + JOURS_MANUTENTION_MER;
            case AIR -> !urgent && distance > SEUIL_LONG_COURRIER_KM ? JOURS_AIR_LONG_COURRIER : JOURS_AIR;
        };
        return Math.max(jours, DELAI_MINIMUM_JOURS);
    }
}

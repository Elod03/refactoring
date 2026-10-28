package fr.efrei.transfret;

public class EstimateurDelai {

    private static final int DELAI_MINIMUM_JOURS = 1;

    public int estimer(int distance, ModeTransport mode, boolean urgent) {
        return Math.max(mode.getDelai().estimer(distance, urgent), DELAI_MINIMUM_JOURS);
    }
}

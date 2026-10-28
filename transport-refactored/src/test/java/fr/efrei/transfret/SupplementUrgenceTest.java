package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementUrgenceTest {

    private static final Prix CENT_EUROS = demande -> 100;

    @Test
    void majoreDeCinquantePourCentSiUrgent() {
        assertEquals(150, new SupplementUrgence(CENT_EUROS).calculer(Demandes.urgente()), 0.001);
    }

    @Test
    void neChangeRienSinon() {
        assertEquals(100, new SupplementUrgence(CENT_EUROS).calculer(Demandes.standard()), 0.001);
    }
}

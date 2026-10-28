package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementHorsGabaritTest {

    private static final Prix CENT_EUROS = demande -> 100;

    @Test
    void majoreSiUnColisDepasseDeuxMetres() {
        assertEquals(110, new SupplementHorsGabarit(CENT_EUROS).calculer(Demandes.horsGabarit()), 0.001);
    }

    @Test
    void neChangeRienSinon() {
        assertEquals(100, new SupplementHorsGabarit(CENT_EUROS).calculer(Demandes.standard()), 0.001);
    }
}

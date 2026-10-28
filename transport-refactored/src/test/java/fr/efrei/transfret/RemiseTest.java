package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemiseTest {

    private static final Prix CENT_EUROS = demande -> 100;

    @Test
    void appliqueLaPolitiqueDuClient() {
        assertEquals(80, new Remise(CENT_EUROS).calculer(Demandes.pour(TypeClient.PREMIUM)), 0.001);
    }

    @Test
    void neRemiseRienAUnParticulier() {
        assertEquals(100, new Remise(CENT_EUROS).calculer(Demandes.standard()), 0.001);
    }
}

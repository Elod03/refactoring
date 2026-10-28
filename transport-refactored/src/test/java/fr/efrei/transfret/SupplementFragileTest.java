package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementFragileTest {

    private static final Prix CENT_EUROS = demande -> 100;

    @Test
    void majoreSiUnColisEstConcerne() {
        assertEquals(115, new SupplementFragile(CENT_EUROS).calculer(Demandes.avec(TypeMarchandise.FRAGILE)), 0.001);
    }

    @Test
    void neChangeRienSinon() {
        assertEquals(100, new SupplementFragile(CENT_EUROS).calculer(Demandes.standard()), 0.001);
    }
}

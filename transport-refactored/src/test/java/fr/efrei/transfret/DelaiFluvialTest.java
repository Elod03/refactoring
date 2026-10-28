package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelaiFluvialTest {

    @Test
    void troisCentSoixanteKmParJourPlusUnJour() {
        assertEquals(2, new DelaiFluvial().estimer(360, false));
        assertEquals(3, new DelaiFluvial().estimer(365, false));
    }
}

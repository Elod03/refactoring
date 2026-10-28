package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifMerTest {

    @Test
    void additionneDistanceEtPoids() {
        assertEquals(1030, new TarifMer().calculer(2000, 1000, 0), 0.001);
    }

    @Test
    void appliqueLeMinimum() {
        assertEquals(500, new TarifMer().calculer(100, 0, 0), 0.001);
    }
}

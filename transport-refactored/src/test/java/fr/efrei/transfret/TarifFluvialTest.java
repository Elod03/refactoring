package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifFluvialTest {

    @Test
    void additionneDistanceEtPoids() {
        assertEquals(420, new TarifFluvial().calculer(1000, 1000, 0), 0.001);
    }

    @Test
    void appliqueLeMinimum() {
        assertEquals(300, new TarifFluvial().calculer(100, 0, 0), 0.001);
    }
}

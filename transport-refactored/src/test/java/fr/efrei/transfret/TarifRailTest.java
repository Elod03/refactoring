package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifRailTest {

    @Test
    void additionneDistanceEtPoids() {
        assertEquals(807, new TarifRail().calculer(1000, 100, 0), 0.001);
    }

    @Test
    void appliqueLeMinimum() {
        assertEquals(150, new TarifRail().calculer(100, 10, 0), 0.001);
    }
}

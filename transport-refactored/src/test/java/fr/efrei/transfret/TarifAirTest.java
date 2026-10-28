package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifAirTest {

    @Test
    void facturePoidsReelSiPlusLourd() {
        assertEquals(960, new TarifAir().calculer(100, 200, 0.1), 0.001);
    }

    @Test
    void facturePoidsVolumetriqueSiPlusLourd() {
        assertEquals(811.5, new TarifAir().calculer(100, 10, 1), 0.001);
    }
}

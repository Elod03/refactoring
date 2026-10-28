package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifRouteTest {

    @Test
    void additionneDistanceEtPoids() {
        assertEquals(125, new TarifRoute().calculer(100, 50, 0), 0.001);
    }
}

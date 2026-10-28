package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrixDeBaseTest {

    @Test
    void appliqueLeTarifDuMode() {
        assertEquals(125, new PrixDeBase().calculer(Demandes.standard()), 0.001);
    }
}

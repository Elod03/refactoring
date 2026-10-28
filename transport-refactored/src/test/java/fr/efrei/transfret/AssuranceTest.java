package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssuranceTest {

    private static final Prix CENT_EUROS = demande -> 100;

    @Test
    void ajouteDeuxPourCentDeLaValeur() {
        assertEquals(120, new Assurance(CENT_EUROS).calculer(Demandes.assuree(1000)), 0.001);
    }

    @Test
    void ajouteAuMoinsQuinzeEuros() {
        assertEquals(115, new Assurance(CENT_EUROS).calculer(Demandes.assuree(100)), 0.001);
    }

    @Test
    void neChangeRienSansAssurance() {
        assertEquals(100, new Assurance(CENT_EUROS).calculer(Demandes.standard()), 0.001);
    }
}

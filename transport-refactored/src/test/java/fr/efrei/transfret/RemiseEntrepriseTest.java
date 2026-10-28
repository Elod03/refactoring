package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemiseEntrepriseTest {

    @Test
    void remiseDixPourCent() {
        assertEquals(900, new RemiseEntreprise().appliquer(1000), 0.001);
    }

    @Test
    void remiseQuinzePourCentAuDelaDeCinqMille() {
        assertEquals(5100, new RemiseEntreprise().appliquer(6000), 0.001);
    }
}

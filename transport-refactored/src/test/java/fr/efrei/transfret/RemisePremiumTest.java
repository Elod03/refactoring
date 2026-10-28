package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemisePremiumTest {

    @Test
    void remiseVingtPourCent() {
        assertEquals(800, new RemisePremium().appliquer(1000), 0.001);
    }
}

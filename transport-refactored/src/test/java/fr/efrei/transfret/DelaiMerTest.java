package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelaiMerTest {

    @Test
    void ajouteDeuxJoursDeManutention() {
        assertEquals(3, new DelaiMer().estimer(720, false));
        assertEquals(4, new DelaiMer().estimer(721, false));
    }
}

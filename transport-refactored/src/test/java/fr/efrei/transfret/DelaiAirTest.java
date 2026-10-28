package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelaiAirTest {

    @Test
    void unJourEnCourtCourrier() {
        assertEquals(1, new DelaiAir().estimer(1000, false));
    }

    @Test
    void deuxJoursEnLongCourrier() {
        assertEquals(2, new DelaiAir().estimer(5000, false));
    }

    @Test
    void unJourSiUrgent() {
        assertEquals(1, new DelaiAir().estimer(5000, true));
    }
}

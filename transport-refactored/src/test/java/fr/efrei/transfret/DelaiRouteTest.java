package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelaiRouteTest {

    @Test
    void neufHeuresDeConduiteParJour() {
        assertEquals(1, new DelaiRoute().estimer(630, false));
        assertEquals(2, new DelaiRoute().estimer(1260, false));
    }

    @Test
    void dixHuitHeuresParJourSiUrgent() {
        assertEquals(1, new DelaiRoute().estimer(1260, true));
    }
}

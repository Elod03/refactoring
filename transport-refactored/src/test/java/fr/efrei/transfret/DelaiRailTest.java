package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DelaiRailTest {

    @Test
    void ajouteUnJourDeManutention() {
        assertEquals(2, new DelaiRail().estimer(1440, false));
    }

    @Test
    void sansManutentionSiUrgent() {
        assertEquals(1, new DelaiRail().estimer(1440, true));
    }
}

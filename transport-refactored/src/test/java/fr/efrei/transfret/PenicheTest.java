package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PenicheTest {

    private final Peniche peniche = new Peniche("PEN-0001", 2500000);

    @Test
    void transporteEnFluvial() {
        assertTrue(peniche.peutTransporter(colis(TypeMarchandise.STANDARD), ModeTransport.FLUVIAL));
    }

    @Test
    void refuseLeRefrigere() {
        assertFalse(peniche.peutTransporter(colis(TypeMarchandise.REFRIGERE), ModeTransport.FLUVIAL));
    }

    @Test
    void refuseLesAutresModes() {
        assertFalse(peniche.peutTransporter(colis(TypeMarchandise.STANDARD), ModeTransport.ROUTE));
    }

    private static List<Colis> colis(TypeMarchandise type) {
        return List.of(new Colis("colis", 1000, 100, 100, 100, type, 1000));
    }
}

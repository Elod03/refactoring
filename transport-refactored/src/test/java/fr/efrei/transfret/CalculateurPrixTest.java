package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateurPrixTest {

    @Test
    void remiseApresSupplementsEtUrgenceAssuranceApresRemise() {
        List<Colis> colis = List.of(new Colis("colis", 100, 50, 50, 50, TypeMarchandise.FRAGILE, 10000));
        double prix = new CalculateurPrix(AssemblagePrix.standard()).calculerPrixHT(colis, 3000, ModeTransport.ROUTE, true, true, TypeClient.ENTREPRISE);
        assertEquals(5493.16, prix, 0.001);
    }
}

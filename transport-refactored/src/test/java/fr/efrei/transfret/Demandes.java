package fr.efrei.transfret;

import java.util.List;

final class Demandes {

    static DemandePrix standard() {
        return demande(TypeMarchandise.STANDARD, 1000, false, false, TypeClient.PARTICULIER);
    }

    static DemandePrix avec(TypeMarchandise type) {
        return demande(type, 1000, false, false, TypeClient.PARTICULIER);
    }

    static DemandePrix urgente() {
        return demande(TypeMarchandise.STANDARD, 1000, true, false, TypeClient.PARTICULIER);
    }

    static DemandePrix assuree(double valeurDeclaree) {
        return demande(TypeMarchandise.STANDARD, valeurDeclaree, false, true, TypeClient.PARTICULIER);
    }

    static DemandePrix pour(TypeClient typeClient) {
        return demande(TypeMarchandise.STANDARD, 1000, false, false, typeClient);
    }

    private static DemandePrix demande(TypeMarchandise type, double valeurDeclaree, boolean urgent, boolean assurance,
                                       TypeClient typeClient) {
        List<Colis> colis = List.of(new Colis("colis", 50, 50, 50, 50, type, valeurDeclaree));
        return new DemandePrix(colis, 100, ModeTransport.ROUTE, urgent, assurance, typeClient);
    }
}

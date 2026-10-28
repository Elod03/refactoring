package fr.efrei.transfret;

public class AssemblagePrix {

    public static Prix standard() {
        return urgenceRemiseAssurance(supplementsMarchandise());
    }

    public static Prix avecHorsGabarit() {
        return urgenceRemiseAssurance(new SupplementHorsGabarit(supplementsMarchandise()));
    }

    private static Prix supplementsMarchandise() {
        return new SupplementRefrigere(new SupplementDangereux(new SupplementFragile(new PrixDeBase())));
    }

    private static Prix urgenceRemiseAssurance(Prix prix) {
        return new Assurance(new Remise(new SupplementUrgence(prix)));
    }
}

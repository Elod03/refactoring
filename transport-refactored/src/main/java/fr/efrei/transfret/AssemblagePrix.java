package fr.efrei.transfret;

public class AssemblagePrix {

    public static Prix standard() {
        return new Assurance(new Remise(new SupplementUrgence(new SupplementRefrigere(
                new SupplementDangereux(new SupplementFragile(new PrixDeBase()))))));
    }
}

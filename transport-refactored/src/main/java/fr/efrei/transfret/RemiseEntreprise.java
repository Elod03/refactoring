package fr.efrei.transfret;

public class RemiseEntreprise implements PolitiqueRemise {

    private static final double SEUIL_REMISE_MAJOREE = 5000;
    private static final double COEF_REMISE_MAJOREE = 0.85;
    private static final double COEF_REMISE = 0.90;

    @Override
    public double appliquer(double prix) {
        if (prix > SEUIL_REMISE_MAJOREE) {
            return prix * COEF_REMISE_MAJOREE;
        }
        return prix * COEF_REMISE;
    }
}

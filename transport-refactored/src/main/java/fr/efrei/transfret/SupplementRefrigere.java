package fr.efrei.transfret;

public class SupplementRefrigere extends DecorateurPrix {

    private static final double COEF = 1.20;

    public SupplementRefrigere(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = enveloppe.calculer(demande);
        if (TypeMarchandise.REFRIGERE.estPresentDans(demande.colis())) {
            return prix * COEF;
        }
        return prix;
    }
}

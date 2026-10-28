package fr.efrei.transfret;

public class SupplementFragile extends DecorateurPrix {

    private static final double COEF = 1.15;

    public SupplementFragile(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = enveloppe.calculer(demande);
        if (TypeMarchandise.FRAGILE.estPresentDans(demande.colis())) {
            return prix * COEF;
        }
        return prix;
    }
}

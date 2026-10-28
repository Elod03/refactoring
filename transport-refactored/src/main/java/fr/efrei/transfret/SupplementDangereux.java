package fr.efrei.transfret;

public class SupplementDangereux extends DecorateurPrix {

    private static final double COEF = 1.30;

    public SupplementDangereux(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = enveloppe.calculer(demande);
        if (TypeMarchandise.DANGEREUX.estPresentDans(demande.colis())) {
            return prix * COEF;
        }
        return prix;
    }
}

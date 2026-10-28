package fr.efrei.transfret;

public class SupplementUrgence extends DecorateurPrix {

    private static final double COEF = 1.5;

    public SupplementUrgence(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = enveloppe.calculer(demande);
        if (demande.urgent()) {
            return prix * COEF;
        }
        return prix;
    }
}

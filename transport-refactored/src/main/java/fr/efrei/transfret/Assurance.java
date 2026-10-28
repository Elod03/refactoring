package fr.efrei.transfret;

public class Assurance extends DecorateurPrix {

    private static final double TAUX = 0.02;
    private static final double MINIMUM = 15;

    public Assurance(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = enveloppe.calculer(demande);
        if (demande.assurance()) {
            return prix + Math.max(demande.valeurDeclaree() * TAUX, MINIMUM);
        }
        return prix;
    }
}

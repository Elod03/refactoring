package fr.efrei.transfret;

public class SupplementHorsGabarit extends DecorateurPrix {

    private static final double COEF = 1.10;
    private static final double DIMENSION_MAX_CM = 200;

    public SupplementHorsGabarit(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = enveloppe.calculer(demande);
        for (Colis c : demande.colis()) {
            if (c.getLongueur() > DIMENSION_MAX_CM || c.getLargeur() > DIMENSION_MAX_CM || c.getHauteur() > DIMENSION_MAX_CM) {
                return prix * COEF;
            }
        }
        return prix;
    }
}

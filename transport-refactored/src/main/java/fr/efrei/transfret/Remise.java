package fr.efrei.transfret;

public class Remise extends DecorateurPrix {

    public Remise(Prix enveloppe) {
        super(enveloppe);
    }

    @Override
    public double calculer(DemandePrix demande) {
        return demande.typeClient().getPolitiqueRemise().appliquer(enveloppe.calculer(demande));
    }
}

package fr.efrei.transfret;

public class PrixDeBase implements Prix {

    @Override
    public double calculer(DemandePrix demande) {
        return demande.mode().getTarif().calculer(demande.distance(), demande.poids(), demande.volume());
    }
}

package fr.efrei.transfret;

import java.util.List;

public class AffectationVehicule {

    private final Flotte flotte;

    public AffectationVehicule(Flotte flotte) {
        this.flotte = flotte;
    }

    public Vehicule trouverVehicule(List<Colis> colis, ModeTransport mode) {
        double poids = 0;
        for (Colis c : colis) {
            poids = poids + c.getPoids();
        }
        boolean dangereux = TypeMarchandise.DANGEREUX.estPresentDans(colis);
        boolean refrigere = TypeMarchandise.REFRIGERE.estPresentDans(colis);
        for (Vehicule v : flotte.getVehicules()) {
            if (!v.estDisponible()) continue;
            if (v.getCapaciteMax() - v.getChargeActuelle() < poids) continue;
            if (mode == ModeTransport.ROUTE) {
                if (v instanceof Camion) {
                    if (refrigere) {
                        if (v instanceof CamionFrigorifique) {
                            return v;
                        }
                    } else if (v instanceof CamionFrigorifique) {
                        continue;
                    } else if (dangereux) {
                        if (v.peutTransporterMatieresDangereuses()) {
                            return v;
                        }
                    } else {
                        return v;
                    }
                }
            } else if (mode == ModeTransport.RAIL) {
                if (v instanceof Train) {
                    return v;
                }
            } else if (mode == ModeTransport.MER) {
                if (v instanceof Bateau) {
                    if (refrigere && !((Bateau) v).aConteneursRefrigeres()) {
                        continue;
                    }
                    return v;
                }
            } else if (mode == ModeTransport.AIR) {
                if (v instanceof Avion) {
                    return v;
                }
            }
        }
        return null;
    }
}

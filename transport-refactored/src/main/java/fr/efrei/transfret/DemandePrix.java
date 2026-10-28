package fr.efrei.transfret;

import java.util.List;

public record DemandePrix(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance,
                          TypeClient typeClient) {

    public double poids() {
        double total = 0;
        for (Colis c : colis) {
            total = total + c.getPoids();
        }
        return total;
    }

    public double volume() {
        double total = 0;
        for (Colis c : colis) {
            total = total + c.getVolume();
        }
        return total;
    }

    public double valeurDeclaree() {
        double total = 0;
        for (Colis c : colis) {
            total = total + c.getValeurDeclaree();
        }
        return total;
    }
}

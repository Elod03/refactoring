package fr.efrei.transfret;

import java.util.List;

public class ValidateurExpedition {

    public boolean valider(Client client, List<Colis> colis, ModeTransport mode, boolean urgent) {
        if (client == null) {
            return refuser("client obligatoire");
        }
        if (colis == null || colis.isEmpty()) {
            return refuser("au moins un colis est nécessaire");
        }
        for (Colis c : colis) {
            if (c.getPoids() <= 0) {
                return refuser("poids invalide pour " + c.getDescription());
            }
        }
        if (TypeMarchandise.DANGEREUX.estPresentDans(colis) && !mode.accepteDangereux()) {
            return refuser("les matières dangereuses sont interdites en " + mode);
        }
        if (TypeMarchandise.REFRIGERE.estPresentDans(colis) && !mode.accepteRefrigere()) {
            return refuser("le transport réfrigéré est impossible en " + mode);
        }
        if (urgent && !mode.accepteUrgence()) {
            return refuser("pas d'option urgente en " + mode);
        }
        return true;
    }

    private boolean refuser(String message) {
        System.out.println("ERREUR : " + message);
        return false;
    }
}

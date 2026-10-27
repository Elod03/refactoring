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
        if (TypeMarchandise.DANGEREUX.estPresentDans(colis) && mode == ModeTransport.AIR) {
            return refuser("les matières dangereuses sont interdites en transport aérien");
        }
        if (TypeMarchandise.REFRIGERE.estPresentDans(colis) && !(mode == ModeTransport.ROUTE || mode == ModeTransport.MER)) {
            return refuser("le transport réfrigéré n'est possible que par route ou par mer");
        }
        if (urgent && mode == ModeTransport.MER) {
            return refuser("pas d'option urgente pour le transport maritime");
        }
        return true;
    }

    private boolean refuser(String message) {
        System.out.println("ERREUR : " + message);
        return false;
    }
}

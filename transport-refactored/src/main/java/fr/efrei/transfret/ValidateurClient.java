package fr.efrei.transfret;

public class ValidateurClient {

    private static final int LONGUEUR_SIRET = 14;

    public boolean valider(String nom, String email, TypeClient type, String siret) {
        if (nom == null || nom.trim().isEmpty()) {
            return refuser("nom obligatoire");
        }
        if (!emailValide(email)) {
            return refuser("email invalide (" + email + ")");
        }
        if (type == null) {
            return refuser("type de client inconnu");
        }
        if (type.exigeSiret() && (siret == null || siret.length() != LONGUEUR_SIRET)) {
            return refuser("SIRET obligatoire (14 chiffres) pour une entreprise");
        }
        return true;
    }

    private static boolean emailValide(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean refuser(String message) {
        System.out.println("ERREUR : " + message);
        return false;
    }
}

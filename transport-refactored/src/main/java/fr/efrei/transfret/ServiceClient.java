package fr.efrei.transfret;

public class ServiceClient {

    private ValidateurClient validateur = new ValidateurClient();
    private Notificateur notificateur = new Notificateur();

    public Client creerClient(String nom, String email, String telephone, String adresse, TypeClient type, String siret) {
        if (!validateur.valider(nom, email, type, siret)) {
            return null;
        }
        Client c = new Client(nom, email, telephone, adresse, type, siret);
        Database.getInstance().sauvegarderClient(c);
        notificateur.bienvenue(c);
        return c;
    }
}

package fr.efrei.transfret;

public class Notificateur {

    public void bienvenue(Client c) {
        EmailService.envoyer(c.getEmail(), "Bienvenue chez TransFret",
                "Bonjour " + c.getNom() + ", votre compte " + c.getType().name().toLowerCase() + " a été créé.");
    }

    public void expeditionCreee(Expedition e) {
        notifier(e.getClient(), "Confirmation de votre expédition " + e.getId(),
                "Votre expédition " + e.getVilleDepart() + " -> " + e.getVilleArrivee() + " (" + e.getMode() + ") a été enregistrée. Montant : " + e.getPrixTTC() + " € TTC.",
                "TransFret : expédition " + e.getId() + " créée, livraison prévue le " + Dates.formater(e.getDateLivraisonPrevue()));
    }

    public void expeditionPartie(Expedition e) {
        notifier(e.getClient(), "Votre expédition " + e.getId() + " est en route",
                "Votre expédition a quitté " + e.getVilleDepart() + ".",
                "TransFret : expédition " + e.getId() + " partie de " + e.getVilleDepart());
    }

    public void expeditionLivree(Expedition e) {
        notifier(e.getClient(), "Votre expédition " + e.getId() + " est livrée",
                "Votre expédition est arrivée à " + e.getVilleArrivee() + ". Merci de votre confiance.",
                "TransFret : expédition " + e.getId() + " livrée à " + e.getVilleArrivee());
    }

    public void expeditionAnnulee(Expedition e) {
        notifier(e.getClient(), "Annulation de votre expédition " + e.getId(),
                "Votre expédition a été annulée. Frais d'annulation : " + e.getFraisAnnulation() + " €",
                "TransFret : expédition " + e.getId() + " annulée");
    }

    private void notifier(Client client, String sujet, String corps, String sms) {
        EmailService.envoyer(client.getEmail(), sujet, corps);
        if (client.getType() == TypeClient.PREMIUM) {
            new SmsService().envoyerSms(client.getTelephone(), sms);
        }
    }
}

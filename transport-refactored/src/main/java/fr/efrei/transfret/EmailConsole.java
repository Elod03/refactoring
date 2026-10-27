package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class EmailConsole implements CanalEmail {

    private static final String SMTP_HOST = "smtp.transfret.fr";
    private static final String EXPEDITEUR = "noreply@transfret.fr";

    private final List<Envoi> journal = new ArrayList<>();

    @Override
    public boolean envoyer(String destinataire, String sujet, String corps) {
        if (destinataire == null || !destinataire.contains("@")) {
            return false;
        }
        System.out.println("[EMAIL via " + SMTP_HOST + "] " + EXPEDITEUR + " -> " + destinataire + " | " + sujet);
        System.out.println("        " + corps);
        journal.add(new Envoi(destinataire, sujet, corps));
        return true;
    }

    public List<Envoi> getJournal() {
        return journal;
    }

    public record Envoi(String destinataire, String sujet, String corps) {
    }
}

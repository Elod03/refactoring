package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class SmsConsole implements CanalSms {

    private static final int LONGUEUR_MAX_SMS = 160;
    private static final String SUFFIXE_TRONQUE = "...";

    private final List<Envoi> journal = new ArrayList<>();

    @Override
    public void envoyer(String numero, String message) {
        if (message.length() > LONGUEUR_MAX_SMS) {
            message = message.substring(0, LONGUEUR_MAX_SMS - SUFFIXE_TRONQUE.length()) + SUFFIXE_TRONQUE;
        }
        System.out.println("[SMS] -> " + numero + " : " + message);
        journal.add(new Envoi(numero, message));
    }

    public List<Envoi> getJournal() {
        return journal;
    }

    public record Envoi(String numero, String message) {
    }
}

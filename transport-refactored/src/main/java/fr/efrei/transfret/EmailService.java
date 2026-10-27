package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class EmailService {

    public static final String SMTP_HOST = "smtp.transfret.fr";
    public static final String EXPEDITEUR = "noreply@transfret.fr";

    public static List<String[]> journal = new ArrayList<String[]>();

    public static boolean envoyer(String destinataire, String sujet, String corps) {
        try {
            if (destinataire == null || destinataire.indexOf("@") < 0) {
                throw new Exception("Adresse invalide");
            }
            System.out.println("[EMAIL via " + SMTP_HOST + "] " + EXPEDITEUR + " -> " + destinataire + " | " + sujet);
            System.out.println("        " + corps);
            journal.add(new String[]{destinataire, sujet, corps});
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package fr.efrei.transfret;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceExpedition {

    public static final double DEVIS_IMPOSSIBLE = -1;

    private static final double COEF_TTC = 1.2;
    private static final double TAUX_FRAIS_ANNULATION = 0.5;

    private static final double KM_PAR_MILLE_NAUTIQUE = 1.852;
    private static final int ROULAGE_PISTE_KM = 2;
    private static final int ALTITUDE_CROISIERE_PIEDS = 35000;
    private static final double TEMPERATURE_REFRIGERATION = 4;

    private final ExpeditionRepository expeditions;
    private final CalculateurPrix calculateur;
    private final AffectationVehicule affectation;
    private final ValidateurExpedition validateur;
    private final EstimateurDelai estimateurDelai;
    private final Notificateur notificateur;
    private final GenerateurIdExpedition generateurId;

    public ServiceExpedition(ExpeditionRepository expeditions, CalculateurPrix calculateur,
                             AffectationVehicule affectation, ValidateurExpedition validateur,
                             EstimateurDelai estimateurDelai, Notificateur notificateur,
                             GenerateurIdExpedition generateurId) {
        this.expeditions = expeditions;
        this.calculateur = calculateur;
        this.affectation = affectation;
        this.validateur = validateur;
        this.estimateurDelai = estimateurDelai;
        this.notificateur = notificateur;
        this.generateurId = generateurId;
    }

    public Expedition creerExpedition(Client client, List<Colis> colis, String villeDepart, String villeArrivee, ModeTransport mode, boolean urgent, boolean assurance) {
        if (!validateur.valider(client, colis, mode, urgent)) {
            return null;
        }
        int distance = TableDistances.getDistance(villeDepart, villeArrivee);
        if (distance == TableDistances.DISTANCE_INCONNUE) {
            System.out.println("ERREUR : trajet inconnu " + villeDepart + " -> " + villeArrivee);
            return null;
        }

        Vehicule v = affectation.trouverVehicule(colis, mode);
        if (v == null) {
            System.out.println("ERREUR : aucun véhicule disponible pour cette expédition");
            return null;
        }
        for (Colis c : colis) {
            v.charger(c);
        }

        Expedition e = new Expedition();
        e.setId(generateurId.suivant());
        e.setClient(client);
        e.setColis(colis);
        e.setVilleDepart(villeDepart);
        e.setVilleArrivee(villeArrivee);
        e.setDistance(distance);
        e.setMode(mode);
        e.setUrgent(urgent);
        e.setAssurance(assurance);
        e.setStatut(StatutExpedition.CREEE);
        e.setVehicule(v);
        e.setPrixHT(calculateur.calculerPrixHT(colis, distance, mode, urgent, assurance, client.getType()));
        e.setPrixTTC(calculerTTC(e.getPrixHT()));
        e.setDateCreation(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(e.getDateCreation());
        cal.add(Calendar.DAY_OF_MONTH, estimateurDelai.estimer(distance, mode, urgent));
        e.setDateLivraisonPrevue(cal.getTime());

        expeditions.sauvegarder(e);
        notificateur.expeditionCreee(e);
        return e;
    }

    public double calculerDevis(List<Colis> colis, String villeDepart, String villeArrivee, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        int distance = TableDistances.getDistance(villeDepart, villeArrivee);
        if (distance == TableDistances.DISTANCE_INCONNUE) {
            return DEVIS_IMPOSSIBLE;
        }
        return calculerTTC(calculateur.calculerPrixHT(colis, distance, mode, urgent, assurance, typeClient));
    }

    public boolean demarrerExpedition(String id) {
        Expedition e = expeditions.trouver(id);
        if (e == null || e.getStatut() != StatutExpedition.CREEE) {
            System.out.println("ERREUR : impossible de démarrer l'expédition " + id);
            return false;
        }
        e.setStatut(StatutExpedition.EN_COURS);
        Vehicule v = e.getVehicule();
        v.setDisponible(false);
        try {
            v.faireLePlein();
        } catch (Exception ex) {
        }
        switch (e.getMode()) {
            case ROUTE, RAIL -> v.rouler(e.getDistance());
            case MER -> v.naviguer((int) (e.getDistance() / KM_PAR_MILLE_NAUTIQUE));
            case AIR -> {
                v.rouler(ROULAGE_PISTE_KM);
                v.voler(ALTITUDE_CROISIERE_PIEDS);
            }
        }
        if (TypeMarchandise.REFRIGERE.estPresentDans(e.getColis())) {
            v.activerRefrigeration(TEMPERATURE_REFRIGERATION);
        }
        expeditions.mettreAJour(e);
        notificateur.expeditionPartie(e);
        return true;
    }

    public boolean livrerExpedition(String id) {
        Expedition e = expeditions.trouver(id);
        if (e == null || e.getStatut() != StatutExpedition.EN_COURS) {
            System.out.println("ERREUR : impossible de livrer l'expédition " + id);
            return false;
        }
        e.setStatut(StatutExpedition.LIVREE);
        libererVehicule(e);
        expeditions.mettreAJour(e);
        notificateur.expeditionLivree(e);
        return true;
    }

    public boolean annulerExpedition(String id) {
        Expedition e = expeditions.trouver(id);
        if (e == null || e.getStatut() == StatutExpedition.LIVREE || e.getStatut() == StatutExpedition.ANNULEE) {
            System.out.println("ERREUR : impossible d'annuler l'expédition " + id);
            return false;
        }
        if (e.getStatut() == StatutExpedition.EN_COURS) {
            e.setFraisAnnulation(Montants.arrondir(e.getPrixTTC() * TAUX_FRAIS_ANNULATION));
        }
        e.setStatut(StatutExpedition.ANNULEE);
        libererVehicule(e);
        expeditions.mettreAJour(e);
        notificateur.expeditionAnnulee(e);
        return true;
    }

    private static double calculerTTC(double prixHT) {
        return Montants.arrondir(prixHT * COEF_TTC);
    }

    private void libererVehicule(Expedition e) {
        for (Colis c : e.getColis()) {
            e.getVehicule().decharger(c);
        }
        e.getVehicule().setDisponible(true);
    }
}

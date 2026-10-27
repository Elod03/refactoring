package fr.efrei.transfret;

import fr.efrei.transport.contrat.Canal;
import fr.efrei.transport.contrat.ColisDto;
import fr.efrei.transport.contrat.DemandeClient;
import fr.efrei.transport.contrat.DemandeDevis;
import fr.efrei.transport.contrat.DemandeExpedition;
import fr.efrei.transport.contrat.EtatExpedition;
import fr.efrei.transport.contrat.Notification;
import fr.efrei.transport.contrat.Statut;
import fr.efrei.transport.contrat.SystemeTransport;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class NouveauSystemeAdapter implements SystemeTransport {

    private ClientRepository clients;
    private ExpeditionRepository expeditions;
    private EmailConsole emails;
    private SmsConsole sms;
    private ServiceClient serviceClient;
    private ServiceExpedition serviceExpedition;
    private GenerateurFacture generateurFacture;

    @Override
    public void reinitialiser() {
        clients = new ClientRepositoryEnMemoire();
        expeditions = new ExpeditionRepositoryEnMemoire();
        emails = new EmailConsole();
        sms = new SmsConsole();
        Notificateur notificateur = new Notificateur(emails, sms);
        serviceClient = new ServiceClient(clients, new ValidateurClient(), notificateur);
        serviceExpedition = new ServiceExpedition(expeditions, new CalculateurPrix(),
                new AffectationVehicule(Flotte.initiale()), new ValidateurExpedition(), new EstimateurDelai(),
                notificateur, new GenerateurIdExpedition());
        generateurFacture = new GenerateurFacture();
    }

    @Override
    public Optional<String> creerClient(DemandeClient d) {
        Client client = serviceClient.creerClient(d.nom(), d.email(), d.telephone(), d.adresse(),
                TypeClient.valueOf(d.type().name()), d.siret());
        return Optional.ofNullable(client).map(Client::getEmail);
    }

    @Override
    public Optional<String> creerExpedition(DemandeExpedition d) {
        Client client = clients.trouver(d.refClient());
        Expedition expedition = serviceExpedition.creerExpedition(client, versColis(d.colis()), d.villeDepart(),
                d.villeArrivee(), ModeTransport.valueOf(d.mode().name()), d.urgent(), d.assurance());
        return Optional.ofNullable(expedition).map(Expedition::getId);
    }

    @Override
    public Optional<EtatExpedition> consulter(String id) {
        Expedition e = expeditions.trouver(id);
        if (e == null) {
            return Optional.empty();
        }
        int delai = (int) ChronoUnit.DAYS.between(jour(e.getDateCreation()), jour(e.getDateLivraisonPrevue()));
        return Optional.of(new EtatExpedition(e.getId(), Statut.valueOf(e.getStatut().name()), e.getPrixHT(),
                e.getPrixTTC(), delai, e.getVehicule().getImmatriculation(), e.getFraisAnnulation()));
    }

    @Override
    public OptionalDouble devisTTC(DemandeDevis d) {
        double devis = serviceExpedition.calculerDevis(versColis(d.colis()), d.villeDepart(), d.villeArrivee(),
                ModeTransport.valueOf(d.mode().name()), d.urgent(), d.assurance(),
                TypeClient.valueOf(d.typeClient().name()));
        return devis == ServiceExpedition.DEVIS_IMPOSSIBLE ? OptionalDouble.empty() : OptionalDouble.of(devis);
    }

    @Override
    public boolean demarrer(String id) {
        return serviceExpedition.demarrerExpedition(id);
    }

    @Override
    public boolean livrer(String id) {
        return serviceExpedition.livrerExpedition(id);
    }

    @Override
    public boolean annuler(String id) {
        return serviceExpedition.annulerExpedition(id);
    }

    @Override
    public Optional<String> facture(String id) {
        return Optional.ofNullable(expeditions.trouver(id)).map(generateurFacture::generer);
    }

    @Override
    public List<Notification> notifications() {
        List<Notification> notifications = new ArrayList<>();
        for (EmailConsole.Envoi email : emails.getJournal()) {
            notifications.add(new Notification(Canal.EMAIL, email.destinataire(), email.sujet()));
        }
        for (SmsConsole.Envoi envoi : sms.getJournal()) {
            notifications.add(new Notification(Canal.SMS, envoi.numero(), envoi.message()));
        }
        return notifications;
    }

    private static LocalDate jour(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static List<Colis> versColis(List<ColisDto> colis) {
        return colis.stream().map(NouveauSystemeAdapter::versColis).collect(Collectors.toCollection(ArrayList::new));
    }

    private static Colis versColis(ColisDto c) {
        return new Colis(c.description(), c.poidsKg(), c.longueurCm(), c.largeurCm(), c.hauteurCm(),
                TypeMarchandise.valueOf(c.type().name()), c.valeurDeclaree());
    }
}

package fr.efrei.transfret;

public enum TypeClient {
    PARTICULIER(new RemiseParticulier(), false, false),
    ENTREPRISE(new RemiseEntreprise(), true, false),
    PREMIUM(new RemisePremium(), false, true);

    private final PolitiqueRemise politiqueRemise;
    private final boolean exigeSiret;
    private final boolean recoitSms;

    TypeClient(PolitiqueRemise politiqueRemise, boolean exigeSiret, boolean recoitSms) {
        this.politiqueRemise = politiqueRemise;
        this.exigeSiret = exigeSiret;
        this.recoitSms = recoitSms;
    }

    public PolitiqueRemise getPolitiqueRemise() {
        return politiqueRemise;
    }

    public boolean exigeSiret() {
        return exigeSiret;
    }

    public boolean recoitSms() {
        return recoitSms;
    }
}

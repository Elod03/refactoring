package fr.efrei.transfret;

public abstract class DecorateurPrix implements Prix {

    protected final Prix enveloppe;

    protected DecorateurPrix(Prix enveloppe) {
        this.enveloppe = enveloppe;
    }
}

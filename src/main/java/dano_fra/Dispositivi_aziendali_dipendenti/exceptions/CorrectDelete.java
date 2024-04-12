package dano_fra.Dispositivi_aziendali_dipendenti.exceptions;

public class CorrectDelete extends RuntimeException {
    public CorrectDelete() {
        super("Dipendente eliminato correttamente");
    }
}

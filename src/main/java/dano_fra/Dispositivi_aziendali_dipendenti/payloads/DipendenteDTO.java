package dano_fra.Dispositivi_aziendali_dipendenti.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record DipendenteDTO(
        @NotEmpty(message = "L'username non può essere vuoto")
        String username,
        @NotEmpty(message = "Il nome non può essere vuoto")
        String nome,
        @NotEmpty(message = "Il cognome non può essere vuoto")
        String cognome,
        @NotEmpty(message = "L'email non può essere vuota")
        @Email
        String email,
        String avatar
) {
}

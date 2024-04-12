package dano_fra.Dispositivi_aziendali_dipendenti.payloads;

import jakarta.validation.constraints.NotEmpty;

public record DispositivoDTO(
        @NotEmpty(message = "La tipologia non può essere vuoto")
        String tipologia,
        @NotEmpty(message = "Lo stato non può essere vuoto")
        String stato
) {
}

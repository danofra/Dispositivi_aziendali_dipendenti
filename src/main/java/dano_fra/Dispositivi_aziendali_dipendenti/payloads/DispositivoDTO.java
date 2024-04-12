package dano_fra.Dispositivi_aziendali_dipendenti.payloads;

import dano_fra.Dispositivi_aziendali_dipendenti.enums.stato;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.tipologia;
import jakarta.validation.constraints.NotEmpty;

public record DispositivoDTO(
        @NotEmpty(message = "La tipologia non può essere vuoto")
        tipologia tipologia,
        @NotEmpty(message = "Lo stato non può essere vuoto")
        stato stato
) {
}

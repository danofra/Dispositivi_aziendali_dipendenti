package dano_fra.Dispositivi_aziendali_dipendenti.repositories;

import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, Integer> {
    boolean existsByEmail(String email);

    Optional<Dipendente> findByEmail(String email);

}

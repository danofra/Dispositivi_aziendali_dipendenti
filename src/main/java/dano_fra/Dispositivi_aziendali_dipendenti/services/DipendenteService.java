package dano_fra.Dispositivi_aziendali_dipendenti.services;

import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dipendente;
import dano_fra.Dispositivi_aziendali_dipendenti.exceptions.NotFoundException;
import dano_fra.Dispositivi_aziendali_dipendenti.payloads.DipendenteDTO;
import dano_fra.Dispositivi_aziendali_dipendenti.repositories.DipendenteDAO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteDAO dipendenteDAO;

    public Dipendente save(DipendenteDTO newDipendente) throws BadRequestException {
        Optional<Dipendente> existingDipendente = this.dipendenteDAO.findByEmail(newDipendente.email());
        if (existingDipendente.isPresent()) {
            throw new BadRequestException("L'email " + newDipendente.email() + " è già in uso!");
        }
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(newDipendente.username());
        dipendente.setNome(newDipendente.nome());
        dipendente.setCognome(newDipendente.cognome());
        dipendente.setEmail(newDipendente.email());
        dipendente.setAvatar("https://ui-avatars.com/api/?name=" + newDipendente.nome() + "+" + newDipendente.cognome());
        return this.dipendenteDAO.save(dipendente);
    }

    public Page<Dipendente> getDipendente(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteDAO.findAll(pageable);
    }


    public Dipendente findById(int authorId) {
        return this.dipendenteDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public void findByIdAndDelete(int id) {
        Dipendente found = this.findById(id);
        this.dipendenteDAO.delete(found);
    }

    public Dipendente findByIdAndUpdate(int id, DipendenteDTO newDipendente) {
        Dipendente dipendente = this.findById(id);
        dipendente.setUsername(newDipendente.username());
        dipendente.setNome(newDipendente.nome());
        dipendente.setCognome(newDipendente.cognome());
        dipendente.setEmail(newDipendente.email());
        dipendente.setAvatar("https://ui-avatars.com/api/?name=" + newDipendente.nome() + "+" + newDipendente.cognome());
        return this.dipendenteDAO.save(dipendente);

    }
}

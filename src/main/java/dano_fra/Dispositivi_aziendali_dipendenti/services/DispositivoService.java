package dano_fra.Dispositivi_aziendali_dipendenti.services;

import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dipendente;
import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dispositivo;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.stato;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.tipologia;
import dano_fra.Dispositivi_aziendali_dipendenti.exceptions.NotFoundException;
import dano_fra.Dispositivi_aziendali_dipendenti.payloads.DispositivoDTO;
import dano_fra.Dispositivi_aziendali_dipendenti.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoDAO dispositivoDAO;
    @Autowired
    private DipendenteService dipendenteService;

    public Dispositivo save(DispositivoDTO newDispositivo) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setStato(stati(newDispositivo.stato()));
        dispositivo.setTipologia(tipologie(newDispositivo.tipologia()));
        return this.dispositivoDAO.save(dispositivo);
    }

    public Page<Dispositivo> getDispositivo(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dispositivoDAO.findAll(pageable);
    }

    public Dispositivo findById(int dispositivoId) {
        return this.dispositivoDAO.findById(dispositivoId).orElseThrow(() -> new NotFoundException(dispositivoId));
    }

    public void findByIdAndDelete(int id) {
        Dispositivo found = this.findById(id);
        this.dispositivoDAO.delete(found);
    }

    public Dispositivo findByIdAndUpdate(int id, DispositivoDTO newDispositivo) {
        Dispositivo dispositivo = this.findById(id);
        dispositivo.setStato(stati(newDispositivo.stato()));
        dispositivo.setTipologia(tipologie(newDispositivo.tipologia()));
        return this.dispositivoDAO.save(dispositivo);
    }

    public Dispositivo findByIdDispositivoDipendenteAndUpdate(int dispositivoId, int dipendenteId) {
        Dispositivo dispositivo = this.findById(dispositivoId);
        Dipendente dipendente = this.dipendenteService.findById(dipendenteId);
        if (dispositivo.getStato() == stato.DISPONIBILE) {
            dispositivo.setStato(stato.ASSEGNATO);
            dispositivo.setDipendente(dipendente);
            dispositivoDAO.save(dispositivo);
        } else {
            throw new NotFoundException("Dispositivo non disponibile");
        }
        return dispositivo;
    }

    public tipologia tipologie(String tipoTipologia) {
        switch (tipoTipologia) {
            case "TELEFONO":
                return tipologia.TELEFONO;
            case "COMPUTER":
                return tipologia.COMPUTER;
            case "TABLET":
                return tipologia.TABLET;
            default:
                return null;
        }
    }

    public stato stati(String tipoStato) {
        switch (tipoStato) {
            case "DISPONIBILE":
                return stato.DISPONIBILE;
            case "IN_MANUTENZIONE":
                return stato.IN_MANUTENZIONE;
            case "ASSEGNATO":
                return stato.ASSEGNATO;
            case "DIMESSO":
                return stato.DISMESSO;
            default:
                return null;
        }
    }

}

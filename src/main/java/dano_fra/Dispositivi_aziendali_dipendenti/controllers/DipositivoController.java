package dano_fra.Dispositivi_aziendali_dipendenti.controllers;

import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dispositivo;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.stato;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.tipologia;
import dano_fra.Dispositivi_aziendali_dipendenti.payloads.DispositivoDTO;
import dano_fra.Dispositivi_aziendali_dipendenti.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivo")
public class DipositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo dispositivo(@RequestBody @Validated DispositivoDTO newDispositivo) throws Exception {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipologia(tipologia.valueOf(newDispositivo.tipologia()));
        dispositivo.setStato(stato.valueOf(newDispositivo.stato()));
        dispositivoService.save(newDispositivo);
        return dispositivo;
    }

//    @PatchMapping("/{dispositivoId}/{dipendenteId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Dispositivo dispositivo(@PathVariable int dispositivoId, @RequestBody @Validated DispositivoDTO newDispositivo) throws Exception {
//        Dispositivo dispositivo = dispositivoService.findById(dispositivoId);
//        dispositivo.setTipologia(tipologia.valueOf(newDispositivo.tipologia()));
//        dispositivo.setStato(stato.valueOf(newDispositivo.stato()));
//        dispositivo.setDipendente(dipendenteId);
//        dispositivoService.save(newDispositivo);
//        return dispositivo;
//    }

    @GetMapping("")
    public Page<Dispositivo> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return this.dispositivoService.getDispositivo(page, size, sortBy);
    }

    @GetMapping("/{dispositivoId}")
    public Dispositivo findById(@PathVariable int dispositivoId) {
        return dispositivoService.findById(dispositivoId);
    }

    @PutMapping("/{dispositivoId}")
    public Dispositivo findAndUpdate(@PathVariable int dispositivoId, @RequestBody DispositivoDTO body) {
        return dispositivoService.findByIdAndUpdate(dispositivoId, body);
    }


    @PatchMapping("/{dispositivoId}/{dipendenteId}")
    public Dispositivo findByIdDispositivoDipendenteAndUpdate(@PathVariable int dispositivoId, @PathVariable int dipendenteId) {
        return dispositivoService.findByIdDispositivoDipendenteAndUpdate(dispositivoId, dipendenteId);
    }

    @DeleteMapping("/{dispositivoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int dispositivoId) {
        dispositivoService.findByIdAndDelete(dispositivoId);
    }


}

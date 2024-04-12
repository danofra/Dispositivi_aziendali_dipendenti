package dano_fra.Dispositivi_aziendali_dipendenti.controllers;

import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dispositivo;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.stato;
import dano_fra.Dispositivi_aziendali_dipendenti.enums.tipologia;
import dano_fra.Dispositivi_aziendali_dipendenti.payloads.DispositivoDTO;
import dano_fra.Dispositivi_aziendali_dipendenti.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivo")
public class DipositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo dispositivo(@RequestBody DispositivoDTO body) throws Exception {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipologia(tipologia.valueOf(body.tipologia()));
        dispositivo.setStato(stato.valueOf(body.stato()));
        dispositivoService.save(body);
        return dispositivo;
    }

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

    @DeleteMapping("/{dispositivoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int dispositivoId) {
        dispositivoService.findByIdAndDelete(dispositivoId);
    }
}

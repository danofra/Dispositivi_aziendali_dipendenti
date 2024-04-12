package dano_fra.Dispositivi_aziendali_dipendenti.controllers;

import dano_fra.Dispositivi_aziendali_dipendenti.entities.Dipendente;
import dano_fra.Dispositivi_aziendali_dipendenti.payloads.DipendenteDTO;
import dano_fra.Dispositivi_aziendali_dipendenti.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente dipendente(@RequestBody DipendenteDTO body) throws Exception {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(body.username());
        dipendente.setNome(body.nome());
        dipendente.setCognome(body.cognome());
        dipendente.setEmail(body.email());
        dipendente.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        dipendenteService.save(body);
        return dipendente;
    }

    @GetMapping("")
    public Page<Dipendente> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendenteService.getDipendente(page, size, sortBy);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable int dipendenteId) {
        return dipendenteService.findById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findAndUpdate(@PathVariable int dipendenteId, @RequestBody DipendenteDTO body) {
        return dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int dipendenteId) {
        dipendenteService.findByIdAndDelete(dipendenteId);
    }

}

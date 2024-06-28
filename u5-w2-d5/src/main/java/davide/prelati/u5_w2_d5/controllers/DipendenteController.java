package davide.prelati.u5_w2_d5.controllers;

import davide.prelati.u5_w2_d5.entities.Dipendente;
import davide.prelati.u5_w2_d5.exceptions.ResourceNotFoundException;
import davide.prelati.u5_w2_d5.sevices.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.getAllDipendenti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id) {
        Dipendente dipendente = dipendenteService.getDipendenteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con questo id: " + id));
        return ResponseEntity.ok(dipendente);
    }

    @PostMapping
    public Dipendente createDipendente(@RequestBody Dipendente dipendente) {
        return dipendenteService.createDipendente(dipendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id, @RequestBody Dipendente dipendenteDetails) {
        Dipendente updatedDipendente = dipendenteService.updateDipendente(id, dipendenteDetails);
        return ResponseEntity.ok(updatedDipendente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
        return ResponseEntity.noContent().build();
    }

}
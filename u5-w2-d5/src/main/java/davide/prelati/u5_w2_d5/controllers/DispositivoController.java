package davide.prelati.u5_w2_d5.controllers;

import davide.prelati.u5_w2_d5.entities.Dispositivo;
import davide.prelati.u5_w2_d5.exceptions.ResourceNotFoundException;
import davide.prelati.u5_w2_d5.sevices.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping
    public List<Dispositivo> getAllDispositivi() {
        return dispositivoService.getAllDispositivi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> getDispositivoById(@PathVariable Long id) {
        Dispositivo dispositivo = dispositivoService.getDispositivoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo not found for this id :: " + id));
        return ResponseEntity.ok(dispositivo);
    }

    @PostMapping
    public Dispositivo createDispositivo(@RequestBody Dispositivo dispositivo) {
        return dispositivoService.createDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> updateDispositivo(@PathVariable Long id, @RequestBody Dispositivo dispositivoDetails) {
        Dispositivo updatedDispositivo = dispositivoService.updateDispositivo(id, dispositivoDetails);
        return ResponseEntity.ok(updatedDispositivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispositivo(@PathVariable Long id) {
        dispositivoService.deleteDispositivo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{dispositivoId}/assign/{dipendenteId}")
    public ResponseEntity<String> assignDispositivoToDipendente(@PathVariable Long dispositivoId, @PathVariable Long dipendenteId) {
        dispositivoService.assignDispositivoToDipendente(dispositivoId, dipendenteId);
        return ResponseEntity.ok("Dispositivo assigned to dipendente successfully");
    }
}
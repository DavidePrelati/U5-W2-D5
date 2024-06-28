package davide.prelati.u5_w2_d5.sevices;

import davide.prelati.u5_w2_d5.entities.Dipendente;
import davide.prelati.u5_w2_d5.entities.Dispositivo;
import davide.prelati.u5_w2_d5.entities.StatoDispositivo;
import davide.prelati.u5_w2_d5.exceptions.ResourceNotFoundException;
import davide.prelati.u5_w2_d5.repositories.DipendenteRepository;
import davide.prelati.u5_w2_d5.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dispositivo> getAllDispositivi() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getDispositivoById(Long id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo createDispositivo(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo updateDispositivo(Long id, Dispositivo dispositivoDetails) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo not found for this id :: " + id));

        dispositivo.setType(dispositivoDetails.getType());
        dispositivo.setStatus(dispositivoDetails.getStatus());

        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDispositivo(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo non trovato con questo id: " + id));

        dispositivoRepository.delete(dispositivo);
    }

    public void assignDispositivoToDipendente(Long dispositivoId, Long dipendenteId) {
        Dispositivo dispositivo = dispositivoRepository.findById(dispositivoId)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo not found for this id :: " + dispositivoId));
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente not found for this id :: " + dipendenteId));

        dispositivo.setDipendente(dipendente);
        dispositivo.setStatus(StatoDispositivo.ASSEGNATO);

        dispositivoRepository.save(dispositivo);
    }
}

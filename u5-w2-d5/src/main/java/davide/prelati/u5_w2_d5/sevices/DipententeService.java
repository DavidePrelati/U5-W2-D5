package davide.prelati.u5_w2_d5.sevices;

import davide.prelati.u5_w2_d5.entities.Dipendente;
import davide.prelati.u5_w2_d5.exceptions.ResourceNotFoundException;
import davide.prelati.u5_w2_d5.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DipententeService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public Optional<Dipendente> getDipendenteById(Long id) {
        return dipendenteRepository.findById(id);
    }

    public Dipendente createDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(Long id, Dipendente dipendenteDetails) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con questo id : " + id));
        dipendente.setNome(dipendenteDetails.getNome());
        dipendente.setCognome(dipendenteDetails.getCognome());
        dipendente.setEmail(dipendenteDetails.getEmail());

        return dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con questo id: " + id));

        dipendenteRepository.delete(dipendente);
    }

}

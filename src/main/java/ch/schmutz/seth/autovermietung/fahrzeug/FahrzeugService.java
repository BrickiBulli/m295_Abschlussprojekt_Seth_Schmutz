package ch.schmutz.seth.autovermietung.fahrzeug;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FahrzeugService {

    private final FahrzeugRepository repository;

    public FahrzeugService(FahrzeugRepository repository) {
        this.repository = repository;
    }

    public List<Fahrzeug> getFahrzeuge() {
        return repository.findByOrderById();
    }

    public Fahrzeug insertFahrzeug(Fahrzeug fahrzeug) {
        return repository.save(fahrzeug);
    }
}

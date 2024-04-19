package ch.schmutz.seth.autovermietung.fahrzeug;

import ch.schmutz.seth.autovermietung.schaeden.Schaden;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Fahrzeug findFahrzeugById(Long id) {
        if(repository.existsById(id)) {
            return repository.findById(id).orElse(null);
        }else{

        throw new NoSuchElementException("Fahrzeug id: " + id + " nicht gefunden");
        }
    }

    public void deleteFahrzeugById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }else{
            throw new NoSuchElementException("Fahrzeug id: " + id + " nicht gefunden");
        }
    }

    public Fahrzeug updateFahrzeugById(Long id, Fahrzeug fahrzeug) {
        if(repository.existsById(id)){
            return repository.save(fahrzeug);
        }
        else{
            throw new NoSuchElementException("Fahrzeug id: " + id + " nicht gefunden");
        }
    }
}

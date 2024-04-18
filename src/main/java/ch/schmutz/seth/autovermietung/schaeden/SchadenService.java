package ch.schmutz.seth.autovermietung.schaeden;

import ch.schmutz.seth.autovermietung.kunde.Kunde;
import ch.schmutz.seth.autovermietung.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchadenService {

    private final SchadenRepository repository;

    public SchadenService(SchadenRepository repository) {
        this.repository = repository;
    }

    public List<Schaden> getSchaden() {
        return repository.findByOrderById();
    }

    public Schaden insertSchaden(Schaden schaden){
        return repository.save(schaden);
    }

    public Schaden findSchadenById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteSchadenById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Schaden id: " + id + " nicht gefunden");
        }
    }

    public Schaden updateSchadenById(Long id, Schaden schaden) {
        if(repository.existsById(id)){
            return repository.save(schaden);
        }
        else{
            throw new IllegalArgumentException("Schaden id: " + id + " nicht gefunden");
        }
    }
}

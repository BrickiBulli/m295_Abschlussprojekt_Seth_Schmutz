package ch.schmutz.seth.autovermietung.schaeden;

import ch.schmutz.seth.autovermietung.kunde.Kunde;
import ch.schmutz.seth.autovermietung.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        if(repository.existsById(id)) {

            return repository.findById(id).orElse(null);
        }else{
            throw new NoSuchElementException("Schaden id: " + id + " nicht gefunden");
        }
    }

    public void deleteSchadenById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }else{
            throw new NoSuchElementException("Schaden id: " + id + " nicht gefunden");
        }
    }

    public Schaden updateSchadenById(Long id, Schaden schaden) {
        if(repository.existsById(id)){
            return repository.save(schaden);
        }
        else{
            throw new NoSuchElementException("Schaden id: " + id + " nicht gefunden");
        }
    }

    public List<Schaden> getSchaedenByFahrzeugId(Long fahrzeugId) {
        return repository.findByFahrzeug_Id(fahrzeugId);
    }

    public Schaden getSchadenById(Long id) {
        if(repository.existsById(id)){
            return repository.findById(id).orElse(null);
        }else{
        throw new NoSuchElementException("Schaden id: " + id + " nicht gefunden");
        }
    }
}

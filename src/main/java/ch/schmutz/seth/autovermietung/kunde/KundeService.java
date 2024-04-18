package ch.schmutz.seth.autovermietung.kunde;


import ch.schmutz.seth.autovermietung.reservation.Reservation;
import ch.schmutz.seth.autovermietung.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {

    private final KundeRepository repository;

    public KundeService(KundeRepository repository) {
        this.repository = repository;
    }

    public List<Kunde> getKunden() {
        return repository.findByOrderById();
    }

    public Kunde createKunde(Kunde kunde) {
        return repository.save(kunde);
    }

    public Kunde getKundenById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteKundenById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Kunde id: " + id + " nicht gefunden");
        }
    }

    public Kunde updateKundenById(Long id, Kunde kunde) {
        if(repository.existsById(id)){
            return repository.save(kunde);
        }
        else{
            throw new IllegalArgumentException("Kunde id: " + id + " nicht gefunden");
        }
    }
}

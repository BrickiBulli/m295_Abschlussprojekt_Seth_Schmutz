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
}

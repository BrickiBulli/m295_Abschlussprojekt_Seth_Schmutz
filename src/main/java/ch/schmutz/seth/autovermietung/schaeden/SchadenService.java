package ch.schmutz.seth.autovermietung.schaeden;

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
}

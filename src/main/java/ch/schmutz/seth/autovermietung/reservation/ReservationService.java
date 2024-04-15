package ch.schmutz.seth.autovermietung.reservation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getReservations() {
        return repository.findByOrderById();
    }
}

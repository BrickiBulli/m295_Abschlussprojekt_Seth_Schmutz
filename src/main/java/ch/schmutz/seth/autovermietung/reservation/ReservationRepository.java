package ch.schmutz.seth.autovermietung.reservation;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOrderById();
}

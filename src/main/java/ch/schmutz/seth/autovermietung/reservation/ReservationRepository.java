package ch.schmutz.seth.autovermietung.reservation;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOrderById();

    List<Reservation> findByKunde_KeyCloakUser(String username);
}



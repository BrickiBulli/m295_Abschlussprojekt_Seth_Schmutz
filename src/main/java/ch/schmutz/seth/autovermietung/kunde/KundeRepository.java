package ch.schmutz.seth.autovermietung.kunde;

import ch.schmutz.seth.autovermietung.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KundeRepository extends JpaRepository<Kunde, Long> {
    List<Kunde> findByOrderById();
}
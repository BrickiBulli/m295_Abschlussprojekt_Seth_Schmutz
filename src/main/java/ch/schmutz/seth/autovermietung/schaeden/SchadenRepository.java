package ch.schmutz.seth.autovermietung.schaeden;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import ch.schmutz.seth.autovermietung.kunde.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchadenRepository extends JpaRepository<Schaden, Long> {
    List<Schaden> findByOrderById();
    List<Schaden> findByFahrzeug_Id(Long id);
}

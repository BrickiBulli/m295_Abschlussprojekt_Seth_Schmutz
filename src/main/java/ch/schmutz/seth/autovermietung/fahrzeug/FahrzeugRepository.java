package ch.schmutz.seth.autovermietung.fahrzeug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FahrzeugRepository extends JpaRepository<Fahrzeug, Long> {
    List<Fahrzeug> findByOrderById();
}

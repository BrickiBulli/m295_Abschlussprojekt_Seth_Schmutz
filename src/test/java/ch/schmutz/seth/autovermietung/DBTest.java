package ch.schmutz.seth.autovermietung;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import ch.schmutz.seth.autovermietung.fahrzeug.FahrzeugRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBTest {

    private long id;

    @Autowired
    private FahrzeugRepository fahrzeugRepository;

    @Test
    @Order(1)
    void insertVehicle() {
        Fahrzeug fahrzeug = this.fahrzeugRepository.save(new Fahrzeug("Ford", "Kuga", "BL 123 456", "123456789"));
        Assertions.assertNotNull(fahrzeug.getId());
        id = fahrzeug.getId();
    }

    @Test
    @Order(2)
    void updateVehicle() {
        Fahrzeug fahrzeug = this.fahrzeugRepository.findById(id).get();
        Assertions.assertNotNull(fahrzeug.getId());
        fahrzeug.setMarke("Mazda");
        fahrzeug.setModel("Miata");
        fahrzeug.setMarke("BL 456 123");
        fahrzeug.setRahmenNr("654321");
        fahrzeug = fahrzeugRepository.save(fahrzeug);
        Assertions.assertNotNull(fahrzeug.getId());
    }

    @Test
    @Order(3)
    void deleteVehicle() {
        fahrzeugRepository.deleteById(id);
        Optional<Fahrzeug> fahrzeug = fahrzeugRepository.findById(id);
        Assertions.assertFalse(fahrzeug.isPresent());
    }
}

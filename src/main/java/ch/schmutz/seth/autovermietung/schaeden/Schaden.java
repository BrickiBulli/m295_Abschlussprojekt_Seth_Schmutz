package ch.schmutz.seth.autovermietung.schaeden;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Schaden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int Schadensstaerke;

    @Column
    private String stelle;

    @Column
    private Boolean ReperaturNotwendig;

    @Column
    private Boolean VonMieterVerursacht;

    @ManyToOne(optional = false)
    private Fahrzeug fahrzeug;
}

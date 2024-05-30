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
    private String schadenArt;

    @Column
    private int schadensstaerke;

    @Column
    private String stelle;

    @Column
    private Boolean reperaturNotwendig;

    @Column
    private Boolean vonMieterVerursacht;

    @ManyToOne(optional = false)
    private Fahrzeug fahrzeug;
}

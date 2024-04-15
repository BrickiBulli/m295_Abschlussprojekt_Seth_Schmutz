package ch.schmutz.seth.autovermietung.reservation;

import ch.schmutz.seth.autovermietung.fahrzeug.Fahrzeug;
import ch.schmutz.seth.autovermietung.kunde.Kunde;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date vonDatum;

    @Column
    private Date bisDatum;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Fahrzeug fahrzeug;

    @ManyToOne(cascade = CascadeType.ALL)
    private Kunde kunde;
}

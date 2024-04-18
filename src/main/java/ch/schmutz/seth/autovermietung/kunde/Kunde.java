package ch.schmutz.seth.autovermietung.kunde;

import ch.schmutz.seth.autovermietung.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String nachName;

    @Column
    private String versicherungsNr;

}

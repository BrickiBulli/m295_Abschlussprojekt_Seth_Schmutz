package ch.schmutz.seth.autovermietung.kunde;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nachName;

    @Column
    private String versicherungsNr;

}

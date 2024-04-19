package ch.schmutz.seth.autovermietung.fahrzeug;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jdk.jfr.DataAmount;
import lombok.Data;

import javax.annotation.processing.Generated;

@Data
@Entity
public class Fahrzeug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String marke;

    @Column
    private String model;

    @Column
    private String autoNr;

    @Column
    private String rahmenNr;

    public Fahrzeug(String marke, String model, String autoNr, String rahmenNr) {
        this.marke = marke;
        this.model = model;
        this.autoNr = autoNr;
        this.rahmenNr = rahmenNr;
    }

    public Fahrzeug() {

    }
}

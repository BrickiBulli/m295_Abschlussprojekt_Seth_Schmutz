package ch.schmutz.seth.autovermietung.schaeden;

import ch.schmutz.seth.autovermietung.fahrzeug.FahrzeugService;
import ch.schmutz.seth.autovermietung.reservation.Reservation;
import ch.schmutz.seth.autovermietung.reservation.ReservationService;
import ch.schmutz.seth.autovermietung.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
public class SchadenController {

    private final SchadenService schadenService;
    private final FahrzeugService fahrzeugService;

    SchadenController(SchadenService schadenService, FahrzeugService fahrzeugService) {this.schadenService = schadenService; this.fahrzeugService = fahrzeugService;}

    @GetMapping("/api/AleSchaeden")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public List<Schaden> alleSchaeden() {
        return schadenService.getSchaden();
    }

    @PostMapping("/api/InsertSchaden")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public Schaden insertSchaden(@RequestBody Schaden schaden) {
        schaden.setFahrzeug(fahrzeugService.findFahrzeugById(schaden.getFahrzeug().getId()));
        return schadenService.insertSchaden(schaden);
    }

    @DeleteMapping("/api/DeleteSchaden/{id}")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public ResponseEntity<Schaden> deleteReservation(@PathVariable Long id) {
        Schaden schaden = schadenService.findSchadenById(id);
        schadenService.deleteSchadenById(id);
        return new ResponseEntity<>(schaden, HttpStatus.OK);
    }

    @PutMapping("/api/SchadenUpdaten")
    @RolesAllowed({Roles.Mitarbeiter, Roles.Admin})
    public ResponseEntity<Schaden> reservationUpdaten(@Valid @RequestBody Schaden schaden){
        return  new ResponseEntity<>(schadenService.updateSchadenById(schaden.getId(), schaden), HttpStatus.OK);
    }

    @GetMapping("/api/SchadenByFahrzeugId/{fahrzeugId}")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public List<Schaden> findSchadenByFahrzeugId(@PathVariable Long fahrzeugId) {
        List<Schaden> schaeden = schadenService.getSchaedenByFahrzeugId(fahrzeugId);
        return schaeden;
    }

    @GetMapping("/api/FindSchadenById/{id}")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public Schaden EinSchaden(@PathVariable Long id) {
        return schadenService.getSchadenById(id);
    }
}

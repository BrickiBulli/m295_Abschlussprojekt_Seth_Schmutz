package ch.schmutz.seth.autovermietung.fahrzeug;

import ch.schmutz.seth.autovermietung.schaeden.Schaden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ch.schmutz.seth.autovermietung.security.Roles;

import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
public class FahrzeugController {

    private final FahrzeugService fahrzeugService;

    FahrzeugController(FahrzeugService fahrzeugService) {this.fahrzeugService = fahrzeugService;}


    @GetMapping("api/AlleFahrzeuge")
    @RolesAllowed({Roles.Admin, Roles.User, Roles.Mitarbeiter})
    public List<Fahrzeug> GetFahrzeuge() {
        return fahrzeugService.getFahrzeuge();
    }

    @PostMapping("api/InsertFahrzeug")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public ResponseEntity<Fahrzeug> insertFahrzeug(@Valid @RequestBody Fahrzeug fahrzeug) {
        Fahrzeug savedFahrzeug = fahrzeugService.insertFahrzeug(fahrzeug);
        return new ResponseEntity<>(savedFahrzeug, HttpStatus.OK);
    }

    @DeleteMapping("/api/DeleteFahrzeug")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public ResponseEntity<Fahrzeug> deleteReservation(@RequestBody Long id) {
        Fahrzeug fahrzeug = fahrzeugService.findFahrzeugById(id);
        fahrzeugService.deleteFahrzeugById(id);
        return new ResponseEntity<>(fahrzeug, HttpStatus.OK);
    }

    @PutMapping("/api/FahrzeugUpdaten")
    @RolesAllowed({Roles.User, Roles.Mitarbeiter, Roles.Admin})
    public ResponseEntity<Fahrzeug> reservationUpdaten(@Valid @RequestBody Fahrzeug fahrzeug){
        return  new ResponseEntity<>(fahrzeugService.updateFahrzeugById(fahrzeug.getId(), fahrzeug), HttpStatus.OK);
    }
}

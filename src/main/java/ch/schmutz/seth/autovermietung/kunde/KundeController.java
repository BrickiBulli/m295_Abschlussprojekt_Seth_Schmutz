package ch.schmutz.seth.autovermietung.kunde;

import ch.schmutz.seth.autovermietung.reservation.Reservation;
import ch.schmutz.seth.autovermietung.reservation.ReservationService;
import ch.schmutz.seth.autovermietung.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
public class KundeController {

    private final KundeService kundeService;

    KundeController(KundeService kundeService) {this.kundeService = kundeService;}

    @GetMapping("api/AlleKunden")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public List<Kunde> alleKunden() {
        return kundeService.getKunden();
    }

    @PostMapping("api/KundeErstellen")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public Kunde kundenErstellen(@Valid @RequestBody Kunde kunde){
        return  kundeService.createKunde(kunde);
    }

    @DeleteMapping("/api/KundeLöschen")
    @RolesAllowed({Roles.User, Roles.Admin, Roles.Mitarbeiter})
    public ResponseEntity<Kunde> kundeLöschen(@Valid @RequestBody Long kundeId){
        Kunde kunde = kundeService.getKundenById(kundeId);
        kundeService.deleteKundenById(kundeId);
        return new ResponseEntity<>(kunde, HttpStatus.OK);
    }

    @PutMapping("/api/KundenUpdaten")
    @RolesAllowed({Roles.User, Roles.Mitarbeiter, Roles.Admin})
    public ResponseEntity<Kunde> kundenUpdaten(@Valid @RequestBody Kunde kunde){
        return  new ResponseEntity<>(kundeService.updateKundenById(kunde.getId(), kunde), HttpStatus.OK);
    }

}

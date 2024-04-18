package ch.schmutz.seth.autovermietung.kunde;

import ch.schmutz.seth.autovermietung.reservation.Reservation;
import ch.schmutz.seth.autovermietung.reservation.ReservationService;
import ch.schmutz.seth.autovermietung.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}

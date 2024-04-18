package ch.schmutz.seth.autovermietung.schaeden;

import ch.schmutz.seth.autovermietung.reservation.Reservation;
import ch.schmutz.seth.autovermietung.reservation.ReservationService;
import ch.schmutz.seth.autovermietung.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
public class SchadenController {

    private final SchadenService schadenService;

    SchadenController(SchadenService schadenService) {this.schadenService = schadenService;}

    @GetMapping("/api/AleSchaeden")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public List<Schaden> alleSchaeden() {
        return schadenService.getSchaden();
    }

    @PostMapping("/api/InsertSchaden")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public Schaden insertSchaden(@RequestBody Schaden schaden) {
        return schadenService.insertSchaden(schaden);
    }
}

package ch.schmutz.seth.autovermietung.reservation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.schmutz.seth.autovermietung.security.Roles;

import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")

public class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {this.reservationService = reservationService;}

    @GetMapping("/api/AlleReservationen")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter})
    public List<Reservation> alleReservationen() {
        return reservationService.getReservations();
    }

    //@GetMapping("/api/InsertReservation")
    //@RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
}

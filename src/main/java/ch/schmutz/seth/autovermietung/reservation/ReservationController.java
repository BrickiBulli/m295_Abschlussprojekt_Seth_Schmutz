package ch.schmutz.seth.autovermietung.reservation;

import ch.schmutz.seth.autovermietung.kunde.Kunde;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ch.schmutz.seth.autovermietung.security.Roles;
import org.yaml.snakeyaml.events.Event;

import java.security.Principal;
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

    @GetMapping("/api/ReservationByKunde")
    @RolesAllowed({Roles.User, Roles.Admin, Roles.Mitarbeiter})
    public List<Reservation> reservationenNachKunden(JwtAuthenticationToken token){
        String username = token.getToken().getClaimAsString("preferred_username");
        return reservationService.findReservationsByKeyCloakUser(username);
    }

    @PostMapping("/api/InsertReservation")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public Reservation insertReservation(@RequestBody Reservation reservation) {
        return reservationService.insertReservation(reservation);
    }

    @DeleteMapping("/api/DeleteReservation/{id}")
    @RolesAllowed({Roles.Admin, Roles.Mitarbeiter, Roles.User})
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.findReservationByID(id);
        reservationService.deleteResservationByID(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PutMapping("/api/ReservationUpdaten")
    @RolesAllowed({Roles.User, Roles.Mitarbeiter, Roles.Admin})
    public ResponseEntity<Reservation> reservationUpdaten(@Valid @RequestBody Reservation reservation){
        return  new ResponseEntity<>(reservationService.updateReservationById(reservation.getId(), reservation), HttpStatus.OK);
    }
}

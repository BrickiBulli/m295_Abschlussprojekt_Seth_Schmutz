package ch.schmutz.seth.autovermietung.fahrzeug;

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
    @RolesAllowed(Roles.Admin)
    public List<Fahrzeug> GetFahrzeuge() {
        return fahrzeugService.getFahrzeuge();
    }

    @PostMapping("api/InsertFahrzeug")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Fahrzeug> insertFahrzeug(@Valid @RequestBody Fahrzeug fahrzeug) {
        Fahrzeug savedFahrzeug = fahrzeugService.insertFahrzeug(fahrzeug);
        return new ResponseEntity<>(savedFahrzeug, HttpStatus.OK);
    }
}

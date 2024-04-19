package ch.schmutz.seth.autovermietung.reservation;

import ch.schmutz.seth.autovermietung.kunde.Kunde;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getReservations() {
        return repository.findByOrderById();
    }

    public Reservation insertReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    public void deleteResservationByID(Long id) {
        if(repository.existsById(id)){

            repository.deleteById(id);
        } else{
        throw new NoSuchElementException("Reservation id: " + id + " nicht gefunden");
        }
    }

    public Reservation findReservationByID(Long id) {
        if(repository.existsById(id)){

            return repository.findById(id).orElse(null);
        } else{
        throw new NoSuchElementException("Reservation id: " + id + " nicht gefunden");
        }
    }

    public List<Reservation> findReservationsByKeyCloakUser(String username) {
        return repository.findByKunde_KeyCloakUser(username);
    }

    public Reservation updateReservationById(Long id, Reservation reservation) {
        if(repository.existsById(id)){
            return repository.save(reservation);
        }
        else{
            throw new NoSuchElementException("Reservation id: " + id + " nicht gefunden");
        }
    }
}

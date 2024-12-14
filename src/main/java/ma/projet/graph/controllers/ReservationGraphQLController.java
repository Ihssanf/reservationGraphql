package ma.projet.graph.controllers;









import lombok.AllArgsConstructor;
import ma.projet.graph.entities.Chambre;
import ma.projet.graph.entities.Client;
import ma.projet.graph.entities.Reservation;
import ma.projet.graph.repositories.ChambreRepository;
import ma.projet.graph.repositories.ClientRepository;
import ma.projet.graph.repositories.ReservationRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ReservationGraphQLController {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final ChambreRepository chambreRepository;
    @QueryMapping
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @QueryMapping
    public Optional<Reservation> getReservation(@Argument Long id) {
        return reservationRepository.findById(id);
    }
    @MutationMapping
    public Reservation createReservation(@Argument Long client, @Argument Long chambre, @Argument String dateDebut, @Argument String dateFin, @Argument String preferences) {
        Optional<Client> optionalClient = clientRepository.findById(client);
        Optional<Chambre> optionalChambre = chambreRepository.findById(chambre);
        if (optionalClient.isPresent() && optionalChambre.isPresent()){
            Client clientEntity = optionalClient.get();
            Chambre chambreEntity = optionalChambre.get();
            Reservation reservation = new Reservation(null, clientEntity, chambreEntity, dateDebut, dateFin, preferences);
            return reservationRepository.save(reservation);
        }
        return null;
    }
    @MutationMapping
    public Reservation updateReservation(@Argument Long id, @Argument Long client, @Argument Long chambre, @Argument String dateDebut, @Argument String dateFin, @Argument String preferences) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            if (client != null){
                Optional<Client> optionalClient = clientRepository.findById(client);
                optionalClient.ifPresent(reservation::setClient);
            }
            if (chambre != null) {
                Optional<Chambre> optionalChambre = chambreRepository.findById(chambre);
                optionalChambre.ifPresent(reservation::setChambre);
            }
            if(dateDebut != null) reservation.setDateDebut(dateDebut);
            if(dateFin != null) reservation.setDateFin(dateFin);
            if(preferences != null) reservation.setPreferences(preferences);
            return reservationRepository.save(reservation);
        }
        return null;
    }
    @MutationMapping
    public boolean deleteReservation(@Argument Long id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
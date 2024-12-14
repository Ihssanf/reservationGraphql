package ma.projet.graph;

import ma.projet.graph.entities.Chambre;
import ma.projet.graph.entities.Client;
import ma.projet.graph.entities.Reservation;
import ma.projet.graph.repositories.ChambreRepository;
import ma.projet.graph.repositories.ClientRepository;
import ma.projet.graph.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ClientRepository clientRepository, ChambreRepository chambreRepository, ReservationRepository reservationRepository) {
		return args -> {
			// Initialisation des clients
			Client client1 = new Client();
			client1.setNom("John");
			client1.setPrenom("Doe");
			client1.setEmail("john.doe@example.com");
			client1.setTelephone("123-456-7890");
			clientRepository.save(client1);

			Client client2 = new Client();
			client2.setNom("Jane");
			client2.setPrenom("Smith");
			client2.setEmail("jane.smith@example.com");
			client2.setTelephone("098-765-4321");
			clientRepository.save(client2);


			// Initialisation des chambres
			Chambre chambre1 = new Chambre();
			chambre1.setType("single");
			chambre1.setPrix(50.0F);
			chambre1.setDisponible(true);
			chambreRepository.save(chambre1);


			Chambre chambre2 = new Chambre();
			chambre2.setType("double");
			chambre2.setPrix(80.0F);
			chambre2.setDisponible(true);
			chambreRepository.save(chambre2);

			Chambre chambre3 = new Chambre();
			chambre3.setType("suite");
			chambre3.setPrix(150.0F);
			chambre3.setDisponible(true);
			chambreRepository.save(chambre3);

			// Initialisation des réservations (exemple)
			if (!clientRepository.findAll().isEmpty() && !chambreRepository.findAll().isEmpty()) {
				Reservation reservation1 = new Reservation();
				reservation1.setClient(client1);
				reservation1.setChambre(chambre1);
				reservation1.setDateDebut("2024-07-15");
				reservation1.setDateFin("2024-07-20");
				reservation1.setPreferences("Vue sur la mer");
				reservationRepository.save(reservation1);
			}


		};
	}
}
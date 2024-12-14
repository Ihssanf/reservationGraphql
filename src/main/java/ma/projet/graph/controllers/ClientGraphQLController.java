package ma.projet.graph.controllers;





import lombok.AllArgsConstructor;
import ma.projet.graph.entities.Client;
import ma.projet.graph.repositories.ClientRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ClientGraphQLController {
    private final ClientRepository clientRepository;

    @QueryMapping
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @QueryMapping
    public Optional<Client> getClient(@Argument Long id) {
        return clientRepository.findById(id);
    }
    @MutationMapping
    public Client createClient(@Argument String nom, @Argument String prenom, @Argument String email, @Argument String telephone) {
        Client client = new Client(null, nom, prenom, email, telephone);
        return clientRepository.save(client);
    }
    @MutationMapping
    public Client updateClient(@Argument Long id, @Argument String nom, @Argument String prenom, @Argument String email, @Argument String telephone) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            if(nom != null) client.setNom(nom);
            if(prenom != null) client.setPrenom(prenom);
            if(email != null) client.setEmail(email);
            if(telephone != null) client.setTelephone(telephone);
            return clientRepository.save(client);
        }
        return null;
    }
    @MutationMapping
    public boolean deleteClient(@Argument Long id) {
        try{
            clientRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
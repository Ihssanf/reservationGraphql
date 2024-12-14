package ma.projet.graph.controllers;




import lombok.AllArgsConstructor;
import ma.projet.graph.entities.Chambre;
import ma.projet.graph.repositories.ChambreRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ChambreGraphQLController {

    private final ChambreRepository chambreRepository;

    @QueryMapping
    public List<Chambre> getChambres() {
        return chambreRepository.findAll();
    }

    @QueryMapping
    public Optional<Chambre> getChambre(@Argument Long id) {
        return chambreRepository.findById(id);
    }
    @MutationMapping
    public Chambre createChambre(@Argument String type, @Argument Float prix, @Argument boolean disponible) {
        Chambre chambre = new Chambre(null, type, prix, disponible);
        return chambreRepository.save(chambre);
    }
    @MutationMapping
    public Chambre updateChambre(@Argument Long id, @Argument String type, @Argument Float prix, @Argument boolean disponible) {
        Optional<Chambre> optionalChambre = chambreRepository.findById(id);
        if(optionalChambre.isPresent()){
            Chambre chambre = optionalChambre.get();
            if(type != null) chambre.setType(type);
            if(prix != null) chambre.setPrix(prix);
            chambre.setDisponible(disponible);
            return chambreRepository.save(chambre);
        }
        return null;
    }
    @MutationMapping
    public boolean deleteChambre(@Argument Long id) {
        try{
            chambreRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
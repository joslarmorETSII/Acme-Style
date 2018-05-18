package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ServiseRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ServiseService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private ServiseRepository serviseRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ActorService actorService;


    // Constructors -----------------------------------------------------------

    public ServiseService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Servise create(){
        Servise result;
        Artist artist;

        artist=artistService.findByPrincipal();
        result = new Servise();
        result.setCreator(artist);
        result.setQuestions(new ArrayList<Question>());
        result.setStores(new ArrayList<Store>());
        result.setSubscriptions(new ArrayList<Subscription>());

        return result;
    }

    public Servise findOne(int serviseId){
        return serviseRepository.findOne(serviseId);
    }

    public Collection<Servise> findAll(){
        return serviseRepository.findAll();
    }


    public Servise save(Servise servise){
        Assert.notNull(servise);

        return serviseRepository.save(servise);
    }

    public void delete(Servise servise){
        Assert.isTrue(actorService.findByPrincipal().equals(servise.getCreator()),"Not the creator of this Service");
        serviseRepository.delete(servise);
    }

    // Other business methods -----------------------------------------------------------------

    public Servise findOneToEdit(int serviseId){
        Servise result;
        Artist principal;

        result = serviseRepository.findOne(serviseId);
        principal = artistService.findByPrincipal();
        Assert.isTrue(result.getSubscriptions().isEmpty(),"Users are subscribed to this Service");
        Assert.isTrue(principal.equals(result.getCreator()),"Not the creator of this Service");

        return result;
    }
}

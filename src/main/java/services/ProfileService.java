package services;

import domain.Actor;
import domain.Event;
import domain.Gallery;
import domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.EventRepository;
import repositories.ProfileRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ProfileService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private ProfileRepository profileRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public ProfileService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Profile create(){
        Profile result;

        result= new Profile();
        result.setGalleries(new ArrayList<Gallery>());
        return  result;
    }

    public Profile findOne(int id){
        return profileRepository.findOne(id);
    }

    public Collection<Profile> findAll(){
        return profileRepository.findAll();
    }

    public void delete(Profile profile){
        Assert.notNull(profile);
        Actor actor= profile.getActor();
        Assert.isTrue(actorService.findByPrincipal().equals(actor));
        profileRepository.delete(profile);
    }

    public Profile save(Profile profile){
        Assert.notNull(profile);
        Actor actor;

        if (profile.getId() != 0) {
            actor = profile.getActor();
            Assert.isTrue(actorService.findByPrincipal().equals(actor));
        }
        return profileRepository.save(profile);

    }

    // Other business methods -------------------------------------------------

    public Profile generateProfileForActors(Actor actor){
        Profile profile;
        profile = create();
        profile.setFullName(actor.getName()+" "+actor.getSurname());
        profile.setActor(actor);
       return profileRepository.save(profile);
    }
}

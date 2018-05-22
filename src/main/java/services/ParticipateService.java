package services;

import domain.Participate;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.EventRepository;
import repositories.ParticipateRepository;
import security.Authority;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class ParticipateService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private ParticipateRepository participateRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public ParticipateService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Participate create(){
        Participate result;

        result= new Participate();
        result.setMoment(new Date());
        return result;
    }

    public Participate findOne(int id){
        return participateRepository.findOne(id);
    }

    public Collection<Participate> findAll(){
        return participateRepository.findAll();
    }

    public void delete(Participate participate){
        User user= participate.getUser();

        Assert.notNull(participate);
        Assert.isTrue(actorService.findByPrincipal().equals(user));
        participateRepository.delete(participate);
    }

    public Participate save(Participate participate){
        User user= participate.getUser();

        Assert.notNull(participate);
        Assert.isTrue(actorService.findByPrincipal().equals(user));
        return participateRepository.save(participate);
    }

    // Other business methods -------------------------------------------------

}
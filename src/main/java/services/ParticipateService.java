package services;

import domain.Participate;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    // Constructors -----------------------------------------------------------

    public ParticipateService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Participate create(){
        Participate result;

        result= new Participate();
        result.setMoment(new Date());
        result.setUser(userService.findByPrincipal());
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
        Assert.isTrue(actorService.checkRole(Authority.ADMINISTRATOR)||actorService.findByPrincipal().equals(user));
        participateRepository.delete(participate);
    }

    public void deleteAll(Collection<Participate> participates){
        participateRepository.delete(participates);
    }

    public Participate save(Participate participate){
        Participate result;

        Assert.notNull(participate);
        participate.setMoment(new Date());
        result =  participateRepository.save(participate);
        messageService.notifyParticipationToUser(userService.findByPrincipal(),participate.getEvent());

        return  result;
    }

    public Participate participateByUserAndEvent(int actorId, int eventId) {
        return participateRepository.participateByUserAndEvent(actorId,eventId);
    }

    // Other business methods -------------------------------------------------

    public void flush() {
        participateRepository.flush();
    }
}

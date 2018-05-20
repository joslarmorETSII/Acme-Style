package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.EventRepository;
import repositories.RaffleRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class EventService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private EventRepository eventRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public EventService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Event create(){
        Event result;

        result= new Event();
        result.setParticipates(new ArrayList<Participate>());
        return result;
    }

    public Event findOne(int id){
        return eventRepository.findOne(id);
    }

    public Collection<Event> findAll(){
        return eventRepository.findAll();
    }

    public void delete(Event event){
        Assert.notNull(event);
        Assert.isTrue(actorService.checkRole(Authority.MANAGER));
        eventRepository.delete(event);
    }

    public Event save(Event event){
        Assert.notNull(event);
        Assert.isTrue(actorService.checkRole(Authority.MANAGER));
        return eventRepository.save(event);
    }

    // Other business methods -------------------------------------------------
}

package services;

import domain.*;
import forms.ParticipateToEventForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import repositories.EventRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class EventService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private EventRepository eventRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private ManagerService managerService;

    // Constructors -----------------------------------------------------------

    public EventService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Event create(){
        Event result;

        result= new Event();
        result.setParticipates(new ArrayList<Participate>());
        result.setManager(managerService.findByPrincipal());
        result.setArtists(new ArrayList<Artist>());
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
        event.setMoment(new Date());
        return eventRepository.save(event);
    }

    public Collection<Event> participatedEvents(int userId) {
        return eventRepository.participatedEvents(userId);
    }



    public CreditCard reconstructParticipate(ParticipateToEventForm participateToEventForm, BindingResult binding) {
        CreditCard creditCard = new CreditCard();

        creditCard.setBrand(participateToEventForm.getBrand());
        creditCard.setCvv(participateToEventForm.getCvv());
        creditCard.setExpirationMonth(participateToEventForm.getExpirationMonth());
        creditCard.setExpirationYear(participateToEventForm.getExpirationYear());
        creditCard.setHolder(participateToEventForm.getHolder());
        creditCard.setNumber(participateToEventForm.getNumber());

        checkMonth(participateToEventForm.getExpirationMonth(),participateToEventForm.getExpirationYear(),binding);

        return creditCard;
    }

    private boolean checkMonth(Integer month, Integer year, BindingResult binding) {
        FieldError error;
        String[] codigos;
        boolean result;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Integer actualMonth = c.get(Calendar.MONTH)+1;
        Integer actualYear = c.get(Calendar.YEAR);

        if (month!=null && year!=null)
            result = actualYear.equals(year) && month<actualMonth;
        else
            result = false;
        if (result) {
            codigos = new String[1];
            codigos[0] = "creditCard.month.invalid";
            error = new FieldError("registerAdvertisementForm", "expirationMonth", month, false, codigos, null, "should not be in the past");
            binding.addError(error);
        }
        return result;
    }

    // Other business methods -------------------------------------------------

    public Collection<Event> searchEventsPerKeyword(String keyword){
        return this.eventRepository.searchEventsPerKeyword(keyword);
    }


    public Event findOneToEdit(int id){
        Event event;
        Manager principal;

        event = eventRepository.findOne(id);
        principal = managerService.findByPrincipal();
        Assert.isTrue(principal.equals(event.getManager()),"Not the creator of the event");
        return event;
    }
}

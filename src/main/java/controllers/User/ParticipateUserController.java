package controllers.User;

import controllers.AbstractController;
import domain.CreditCard;
import domain.Event;
import domain.Participate;
import domain.User;
import forms.ParticipateToEventForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.ParticipateService;
import services.UserService;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/participate/user")
public class ParticipateUserController extends AbstractController {

    // Supporting services --------------------------------------

    @Autowired
    private ParticipateService participateService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;



    // Constructor ----------------------------------------------

    public ParticipateUserController() { super(); }


    // Subscribe ---------------------------------------------------------------

    @RequestMapping(value = "/participate", method = RequestMethod.GET)
    public ModelAndView subscribe(@RequestParam int eventId) {

        ModelAndView result;
        Event event;

        event = eventService.findOne(eventId);
        Participate participate1 = eventService.checkParticipation(event);
        Assert.isNull(participate1,"already participating in this event");
        Assert.notNull(event);
        Assert.isTrue(event.getCelebrationDate().after(new Date()),"The Event has Started");

        ParticipateToEventForm participateToEventForm = new ParticipateToEventForm();
        participateToEventForm.setEvent(event);

        result = new ModelAndView("event/participate");
        result.addObject("participateToEventForm", participateToEventForm);

        return result;
    }

    @RequestMapping(value = "/unparticipate", method = RequestMethod.GET)
    public ModelAndView unsubscribe(@RequestParam int eventId) {
        ModelAndView result;
        Event event;
        User principal;

        event = eventService.findOne(eventId);
        Assert.notNull(event);
        Assert.isTrue(event.getCelebrationDate().after(new Date()),"The Event has Started");

        principal = userService.findByPrincipal();
        Participate subscription = participateService.participateByUserAndEvent(principal.getId(),eventId);
        participateService.delete(subscription);

        result = new ModelAndView("redirect:../../event/user/listParticipated.do");

        return result;
    }

    // Save ------------------------------------------------------------------------

    @RequestMapping(value = "/participate", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid ParticipateToEventForm participateToEventForm, BindingResult binding) {
        ModelAndView result;
        Participate participate;
        Event event;

        try {
            CreditCard creditCard = eventService.reconstructParticipate(participateToEventForm, binding);

            if (binding.hasErrors())
                result = this.createEditModelAndView2(participateToEventForm, null);
            else {



                result = new ModelAndView("redirect:../../event/user/list.do");
                event = participateToEventForm.getEvent();
                participate = participateService.create();
                participate.setCreditCard(creditCard);
                participate.setEvent(event);
                participateService.save(participate);
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView2(participateToEventForm, "general.commit.error");
        }

        return result;
    }


    private ModelAndView createEditModelAndView2(ParticipateToEventForm participateToEventForm, String messageCode) {
        ModelAndView result;

        result = new ModelAndView("event/participate");
        result.addObject("participateToEventForm", participateToEventForm);
        result.addObject("message", messageCode);

        return result;
    }
}

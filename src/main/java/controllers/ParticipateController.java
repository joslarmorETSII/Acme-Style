package controllers;

import controllers.AbstractController;
import domain.CreditCard;
import domain.Event;
import domain.Participate;
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

import javax.validation.Valid;
@Controller
@RequestMapping("/participate/user")
public class ParticipateController extends AbstractController {

    // Supporting services --------------------------------------

    @Autowired
    private ParticipateService participateService;

    @Autowired
    private EventService eventService;



    // Constructor ----------------------------------------------



    // Subscribe ---------------------------------------------------------------

    @RequestMapping(value = "/participate", method = RequestMethod.GET)
    public ModelAndView subscribe(@RequestParam int eventId) {

        ModelAndView result;
        Event event;

        event = eventService.findOne(eventId);
        Assert.notNull(event);

        ParticipateToEventForm participateToEventForm = new ParticipateToEventForm();
        participateToEventForm.setEvent(event);

        result = new ModelAndView("event/participate");
        result.addObject("participateToEventForm", participateToEventForm);

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
                result = new ModelAndView("redirect: ../../event/list.do");
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

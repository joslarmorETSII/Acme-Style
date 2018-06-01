package controllers;

import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;

import java.util.Collection;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController{

    // Supporting Services --------------------------------

    @Autowired
    private EventService eventService;


    // Constructor -----------------------------------------

    public EventController() {
        super();
    }

    // Listing ----------------------------------------------

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView result;
        Collection<Event> events;

        events = eventService.findAll();

        result = new ModelAndView("event/list");
        result.addObject("events", events);
        result.addObject("requestURI", "event/listAll.do");

        return result;
    }
}

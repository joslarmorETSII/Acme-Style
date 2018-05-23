package controllers;

import domain.Event;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.UserService;

import java.util.Collection;

@Controller
@RequestMapping("/event/user")
public class EventUserController extends AbstractController {

    // Supporting Services --------------------------------

    @Autowired
    private EventService eventService;

   @Autowired
   private UserService userService;

    // Constructor -----------------------------------------

    public EventUserController() {
        super();
    }

    // Listing ----------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listAll(){
        ModelAndView result;
        User user;
        Collection<Event> events;

        user = userService.findByPrincipal();
        events = eventService.findAll();
        events.removeAll(eventService.participatedEvents(user.getId()));

        result = new ModelAndView("event/list");
        result.addObject("events",events);
        result.addObject("requestURI","event/user/list.do");
        result.addObject("notParticipated",true);

        return result;
    }
    @RequestMapping(value = "/listParticipated", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView result;
        User user;
        Collection<Event> events;

        user = userService.findByPrincipal();
        events = eventService.participatedEvents(user.getId());
        result = new ModelAndView("event/list");
        result.addObject("events",events);
        result.addObject("requestURI","event/user/list.do");

        return result;
    }

}

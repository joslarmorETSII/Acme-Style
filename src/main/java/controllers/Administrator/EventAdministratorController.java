package controllers.Administrator;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.*;

import java.util.Collection;

@Controller
@RequestMapping("/event/administrator")
public class EventAdministratorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private EventService eventService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ActorService actorService;

    // Constructor --------------------------------------------

    public EventAdministratorController() {
        super();
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;


        Collection<Event> allEvents = eventService.findAll();


        result = new ModelAndView("event/list");
        result.addObject("events", allEvents);
        result.addObject("requestURI","event/administrator/list.do");

        return result;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int eventId){
        ModelAndView result;
        Event event= eventService.findOneToEditAdmin(eventId);
        try{

            eventService.deleteAdmin(event);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(event,"general.commit.error");
        }

        return result;
    }

    // Display ----------------------------------------------------------------

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam int eventId) {
        ModelAndView result;
        Event event;

        event = this.eventService.findOne(eventId);

        Actor actor=actorService.findByPrincipal();
        Administrator admin = administratorService.findByPrincipal();
        Assert.isTrue(actor.equals(admin));

        result = new ModelAndView("event/display");
        result.addObject("event", event);
        result.addObject("cancelURI","event/listAll.do");

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Event event) {
        ModelAndView result;

        result = this.createEditModelAndView(event, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Event event, final String messageCode) {
        ModelAndView result;



        Collection<Event> events = this.eventService.findAll();

        result = new ModelAndView("event/edit");
        result.addObject("event",event);
        result.addObject("events", events);
        result.addObject("message", messageCode);

        return result;
    }


}

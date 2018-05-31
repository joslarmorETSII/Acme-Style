package controllers.manager;


import controllers.AbstractController;
import domain.Event;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.ManagerService;
import services.StoreService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/event/manager")
public class EventManagerController extends AbstractController{

    // Services --------------------------------------------

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StoreService storeService;

    // Constructor -----------------------------------------

    public EventManagerController() {
        super();
    }


    // Creation ---------------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Event event;


        event = eventService.create();

        result = createEditModelAndView(event);

        return result;
    }

    // Edit ------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int eventId){
        ModelAndView result;
        Event event;
        Manager manager;

        manager = managerService.findByPrincipal();
        event = eventService.findOneToEdit(eventId);
        result = new ModelAndView("event/edit");
        result.addObject("event",event);
        result.addObject("stores",manager.getStores());

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "save")
    public ModelAndView edit(@Valid Event event, BindingResult binding){
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(event);
        } else
            try {
                eventService.save(event);
                result = new ModelAndView("redirect: /event/manager/list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(event, "general.commit.error");
            }

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "delete")
    public ModelAndView delete( Event event){
        ModelAndView result;

        try {
            eventService.delete(event);
            result = list();
        } catch (Throwable oops) {
            result = createEditModelAndView(event, "general.commit.error");
        }

        return result;
    }

    // Listing ----------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView result;
        Manager manager;
        Collection<Event> events;

        manager = managerService.findByPrincipal();
        events = manager.getEvents();
        result = new ModelAndView("event/list");
        result.addObject("events",events);
        result.addObject("requestURI","event/manager/list.do");

        return  result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(Event event) {
        return createEditModelAndView(event, null);
    }

    protected ModelAndView createEditModelAndView(Event event ,  String text) {
        ModelAndView result;
        Manager manager;

        manager = managerService.findByPrincipal();
        result = new ModelAndView("event/edit");
        result.addObject("event", event);
        result.addObject("message", text);
        result.addObject("requestURI", "event/manager/edit.do");
        result.addObject("cancelURI","event/manager/list.do");
        result.addObject("stores",manager.getStores());

        return result;
    }
}

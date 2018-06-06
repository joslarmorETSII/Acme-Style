package controllers.manager;

import controllers.AbstractController;
import domain.Manager;
import domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.ManagerService;
import services.ServiseService;
import services.StoreService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/store/manager")
public class StoreManagerController extends AbstractController{


    // Services --------------------------------------------

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ServiseService serviseService;

    // Constructor ------------------------------------------

    public StoreManagerController() { super(); }

    // Creation ---------------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Store store;


        store = storeService.create();

        result = createEditModelAndView(store);

        return result;
    }

    // Edit ------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int storeId){
        ModelAndView result;
        Store store;
        Manager manager;

        manager = managerService.findByPrincipal();
        store = storeService.findOneToedit(storeId);
        result = new ModelAndView("store/edit");
        result.addObject("store",store);
        result.addObject("events",manager.getEvents());
        result.addObject("servises",serviseService.findAll());

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "save")
    public ModelAndView edit(@Valid Store store, BindingResult binding){
        ModelAndView result;
        storeService.checkMonth(store.getCreditCard().getExpirationMonth(),store.getCreditCard().getExpirationYear(),binding);
        if (binding.hasErrors()) {
            result = createEditModelAndView(store);
        } else
            try {
                storeService.save(store);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(store, "general.commit.error");
            }

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "delete")
    public ModelAndView delete( Store store){
        ModelAndView result;

        try {
            storeService.delete(store);
            result = list();
        } catch (Throwable oops) {
            result = createEditModelAndView(store, "general.commit.error");
        }

        return result;
    }

    // Listing ----------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView result;
        Manager manager;
        Collection<Store> stores;

        manager = managerService.findByPrincipal();
        stores = manager.getStores();
        result = new ModelAndView("store/list");
        result.addObject("stores",stores);

        return  result;
    }


    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(Store store) {
        return createEditModelAndView(store, null);
    }

    protected ModelAndView createEditModelAndView(Store store ,  String text) {
        ModelAndView result;
        Manager manager;

        manager = managerService.findByPrincipal();
        result = new ModelAndView("store/edit");
        result.addObject("store", store);
        result.addObject("message", text);
        result.addObject("requestURI", "store/manager/edit.do");
        result.addObject("cancelURI","store/manager/list.do");
        result.addObject("events",manager.getEvents());
        result.addObject("servises",serviseService.findAll());

        return result;
    }
}

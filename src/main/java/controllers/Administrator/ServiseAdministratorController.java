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
import services.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/servise/administrator")
public class ServiseAdministratorController extends AbstractController {
    // Services --------------------------------------------

    @Autowired
    private ServiseService serviseService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ActorService actorService;



    // Constructor --------------------------------------------

    public ServiseAdministratorController() {
        super();
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;

        Collection<Servise> servisesTaboo = serviseService.servisesTaboo();
        Collection<Servise> allServises = serviseService.findAll();


        result = new ModelAndView("servise/list");
        result.addObject("servises", servisesTaboo);
        result.addObject("allServises", allServises);
        result.addObject("requestURI","servise/administrator/list.do");

        return result;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int serviseId){
        ModelAndView result;
        Servise servise= serviseService.findOne(serviseId);
        try{

            serviseService.delete(servise);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(servise,"general.commit.error");
        }

        return result;
    }

    // Display ----------------------------------------------------------------

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam int serviseId) {
        ModelAndView result;
        Servise servise;
        Double finalPrice;

        servise = this.serviseService.findOne(serviseId);
        finalPrice=serviseService.finalPrice(servise);

        Actor actor=actorService.findByPrincipal();
        Administrator admin = administratorService.findByPrincipal();
        Assert.isTrue(actor.equals(admin));

        result = new ModelAndView("servise/display");
        result.addObject("servise", servise);
        result.addObject("finalPrice",finalPrice);
        result.addObject("cancelURI","servise/listServisesPublished.do");

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Servise servise) {
        ModelAndView result;

        result = this.createEditModelAndView(servise, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Servise servise, final String messageCode) {
        ModelAndView result;
        Artist creator = artistService.findByPrincipal();

        Collection<Servise> servises = this.serviseService.servisesPerCreator(creator.getId());

        result = new ModelAndView("servise/edit");
        result.addObject("servise", servise);
        result.addObject("servises", servises);
        result.addObject("actionURI","servise/administrator/edit.do");
        result.addObject("message", messageCode);

        return result;
    }
}

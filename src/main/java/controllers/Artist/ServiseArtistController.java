package controllers.Artist;


import controllers.AbstractController;
import domain.Artist;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ArtistService;
import services.ServiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/servise/artist")
public class ServiseArtistController extends AbstractController{

    // Services --------------------------------------------

    @Autowired
    private ServiseService serviseService;

    @Autowired
    private ArtistService artistService;

    // Constructor --------------------------------------------

    public ServiseArtistController() {
        super();
    }

    // Creation ------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Servise servise= null ;

        servise = this.serviseService.create();
        result = this.createEditModelAndView(servise);

        return result;
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        Artist artist;
        Collection<Servise> servises=new ArrayList<>();

        SimpleDateFormat formatterEs;
        SimpleDateFormat formatterEn;
        String momentEs;
        String momentEn;

        formatterEs = new SimpleDateFormat("dd/MM/yyyy");
        momentEs = formatterEs.format(new Date());
        formatterEn = new SimpleDateFormat("yyyy/MM/dd");
        momentEn = formatterEn.format(new Date());

        artist = artistService.findByPrincipal();
        servises=artist.getServises();



        result = new ModelAndView("servise/list");
        result.addObject("servises", servises);
        result.addObject("artist",artist);
        result.addObject("requestURI","servise/artist/list.do");
        result.addObject("momentEs", momentEs);
        result.addObject("momentEn", momentEn);

        return result;

    }


    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int serviseId) {
        final ModelAndView result;
        Servise servise;
        servise = this.serviseService.findOneToEdit(serviseId);
        Assert.notNull(servise);
        result = this.createEditModelAndView(servise);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(Servise servisePruned, final BindingResult binding) {
        ModelAndView result;



        try {
            Servise servise = this.serviseService.reconstructS(servisePruned,binding);

            if (binding.hasErrors()){
                result = this.createEditModelAndView(servisePruned);
            }
            else {
                this.serviseService.save(servise);
                result = new ModelAndView("redirect:list.do");
            }
        } catch (final Throwable oops) {
            if (binding.hasErrors()){
                result = this.createEditModelAndView(servisePruned);
            }else{
                result = this.createEditModelAndView(servisePruned, "general.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "delete")
    public ModelAndView edit(Servise servisePruned){
        ModelAndView result;

        Servise servise = this.serviseService.findOne(servisePruned.getId());
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
    public ModelAndView display(@RequestParam final int serviseId) {
        ModelAndView result;
        Servise servise= null;



        servise = this.serviseService.findOne(serviseId);



        result = new ModelAndView("servise/display");
        result.addObject("servise", servise);
        result.addObject("cancelURI", "servise/artist/list.do");


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
        result = new ModelAndView("servise/edit");
        result.addObject("servise", servise);
        result.addObject("message", messageCode);
        result.addObject("actionURI","servise/artist/edit.do");
        result.addObject("cancelURI", "servise/artist/list.do");

        return result;
    }
}

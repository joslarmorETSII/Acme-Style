package controllers;

import domain.Artist;
import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ArtistService;

import javax.validation.Valid;

@Controller
@RequestMapping("/artist")
public class ArtistController extends AbstractController{

    // Services --------------------------------------------

    @Autowired
    private ArtistService artistService;

    // Constructor -----------------------------------------

    public ArtistController() {
            super();
    }

    //Register --------------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView edit() {

        ModelAndView result;

        result = new ModelAndView("artist/register");
        result.addObject("userForm", new UserForm());

        return result;
    }

    // Save ------------------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid UserForm userForm, BindingResult binding) {
        ModelAndView result;
        Artist artist;

        try {
            artist = artistService.reconstruct(userForm, binding);

            if (binding.hasErrors())
                result = createEditModelAndView(userForm);
            else {
                result = new ModelAndView("redirect:/security/login.do");
                artistService.save(artist);
            }
        } catch (final Throwable oops) {
            if(oops.getCause().getCause().getMessage().contains("Duplicate entry"))
                result = createEditModelAndView(userForm,"general.duplicated.username");
            else
                result = this.createEditModelAndView(userForm, "general.commit.error");
        }

        return result;
    }

    // Ancillary methods ------------------------------------------------------------

    private ModelAndView createEditModelAndView( UserForm userForm) {
        return createEditModelAndView(userForm,null);
    }

    private ModelAndView createEditModelAndView( UserForm userForm,  String message) {

        ModelAndView result = new ModelAndView("artist/register");

        result.addObject("userForm", userForm);
        result.addObject("message", message);
        return result;
    }
}

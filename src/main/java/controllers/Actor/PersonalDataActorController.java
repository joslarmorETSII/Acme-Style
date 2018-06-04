package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import forms.ActorForm;
import forms.UserAccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;


import javax.validation.Valid;

@Controller
@RequestMapping("/personalData/actor")
public class PersonalDataActorController extends AbstractController {

    // Supporting services --------------------------------------

    @Autowired
    private ActorService actorService;



    // Constructor ----------------------------------------------

    public PersonalDataActorController() { super(); }


    // edit -----------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView result;
        ActorForm actorForm;
        Actor actor;

        actor = actorService.findByPrincipal();
        actorForm =  new ActorForm();
        actorForm.setName(actor.getName());
        actorForm.setSurname(actor.getSurname());
        actorForm.setEmail(actor.getEmail());
        actorForm.setPhone(actor.getPhone());
        actorForm.setPostalAddresses(actor.getPostalAddresses());
        result = new ModelAndView("profile/personalData");
        result.addObject("actorForm",actorForm);

        return result;
    }

    // Save ------------------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid ActorForm actorForm, BindingResult binding) {
        ModelAndView result;
        Actor actor;
        if (binding.hasErrors())
            result = createEditModelAndView(actorForm);
        else
            try {
            actor = actorService.reconstruct(actorForm, binding);
            result = new ModelAndView("redirect: /welcome/index.do");
            actorService.save(actor);

        } catch (final Throwable oops) {
            result = this.createEditModelAndView(actorForm, "general.commit.error");
        }

        return result;
    }

    // Ancillary methods ------------------------------------------------------------

    private ModelAndView createEditModelAndView( ActorForm actorForm) {
        return createEditModelAndView(actorForm,null);
    }

    private ModelAndView createEditModelAndView( ActorForm actorForm,  String message) {

        ModelAndView result = new ModelAndView("profile/personalData");

        result.addObject("actorForm", actorForm);
        result.addObject("message", message);
        return result;
    }
}

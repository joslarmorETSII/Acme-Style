package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import forms.UserAccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import security.UserAccount;
import services.ActorService;
import services.UserAccountService;

import javax.validation.Valid;

@Controller
@RequestMapping("/userAccount/actor")
public class UserAccountActorController extends AbstractController {

    // Supporting services --------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private UserAccountService userAccountService;



    // Constructor ----------------------------------------------

    public UserAccountActorController() { super(); }


    // edit -----------------------------------------------------

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public ModelAndView userAccount() {
        ModelAndView result;
        UserAccountForm userAccountForm;
        Actor actor;

        actor = actorService.findByPrincipal();
        userAccountForm =  new UserAccountForm();
        userAccountForm.setUsername(actor.getUserAccount().getUsername());

        result = new ModelAndView("profile/userAccount");
        result.addObject("userAccountForm",userAccountForm);

        return result;
    }

    // userAccount save ------------------------------------------------------------------------

    @RequestMapping(value = "/change", method = RequestMethod.POST, params = "save")
    public ModelAndView userAccount(@Valid UserAccountForm userAccountForm, BindingResult binding) {
        ModelAndView result;
        UserAccount userAccount;
        Actor actor;

        try {
            userAccount = actorService.reconstructUserAccountForm(userAccountForm, binding);
            if (binding.hasErrors())
                result = createEditModelAndView(userAccountForm);
            else {
                result = new ModelAndView("redirect: /welcome/index.do");
                userAccountService.save(userAccount);
            }

        } catch (final Throwable oops) {
            result = this.createEditModelAndView(userAccountForm, "general.commit.error");
        }

        return result;
    }


    // Ancillary methods ------------------------------------------------------------

    private ModelAndView createEditModelAndView( UserAccountForm userAccountForm) {
        return createEditModelAndView(userAccountForm,null);
    }

    private ModelAndView createEditModelAndView( UserAccountForm userAccountForm,  String message) {

        ModelAndView result = new ModelAndView("profile/userAccount");

        result.addObject("userAccountForm", userAccountForm);
        result.addObject("message", message);
        return result;
    }

}

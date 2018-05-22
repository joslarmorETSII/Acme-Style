package controllers;

import domain.User;
import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{

    // Services --------------------------------------------

    @Autowired
    private UserService userService;

    // Constructor -----------------------------------------

    public UserController() {
        super();
    }

    //Register --------------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView edit() {

        ModelAndView result;

        result = new ModelAndView("user/register");
        result.addObject("userForm", new UserForm());

        return result;
    }

    // Save ------------------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final UserForm userForm, BindingResult binding) {
        ModelAndView result;
        User user;

        try {
            user = this.userService.reconstruct(userForm, binding);

            if (binding.hasErrors())
                result = createEditModelAndView(userForm);
            else {
                result = new ModelAndView("redirect:/security/login.do");

                user = this.userService.save(user);

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

        ModelAndView result = new ModelAndView("user/register");

        result.addObject("userForm", userForm);
        result.addObject("message", message);
        return result;
    }


}

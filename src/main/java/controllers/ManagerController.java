package controllers;

import domain.Manager;
import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ManagerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/managr")
public class ManagerController extends AbstractController{

    // Services --------------------------------------------

    @Autowired
    private ManagerService managerService;

    // Constructor -----------------------------------------

    public ManagerController() {
        super();
    }

    //Register --------------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView edit() {

        ModelAndView result;

        result = new ModelAndView("manager/register");
        result.addObject("userForm", new UserForm());

        return result;
    }

    // Save ------------------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid  UserForm userForm, BindingResult binding) {
        ModelAndView result;
        Manager manager;

        try {
            manager = managerService.reconstruct(userForm, binding);

            if (binding.hasErrors())
                result = createEditModelAndView(userForm);
            else {
                result = new ModelAndView("redirect:/welcome/index.do");

                manager = this.managerService.save(manager);

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

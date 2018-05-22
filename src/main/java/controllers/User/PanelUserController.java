package controllers.User;

import controllers.AbstractController;
import domain.Panel;
import domain.Panel;
import domain.Photo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PanelService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/panel/user")
public class PanelUserController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private PanelService panelService;

    @Autowired
    private UserService userService;

    // Constructor -----------------------------------------

    public PanelUserController() {
        super();
    }

    // Creation ------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Panel panel;

        panel = this.panelService.create();
        result = this.createEditModelAndView(panel);

        return result;
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView result;

        Collection<Panel> panels= new ArrayList<Panel>();

        panels=this.panelService.findAll();

        result = new ModelAndView("panel/list");
        result.addObject("panels", panels);
        result.addObject("requestURI","panel/user/list.do");

        return result;

    }

    // Display ----------------------------------------------------------------

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam final int panelId) {
        ModelAndView result;
        Panel panel;
        Collection<Photo> photos;

        panel = this.panelService.findOne(panelId);
        Assert.notNull(panel);

        photos = panel.getPhotos();

        result = new ModelAndView("panel/display");
        result.addObject("photos", photos);
        result.addObject("panel",panel);
        result.addObject("cancelURI", "panel/user/list.do");

        return result;
    }

    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int panelId) {
        final ModelAndView result;
        Panel panel;
        panel = this.panelService.findOneToEdit(panelId);
        Assert.notNull(panel);
        result = this.createEditModelAndView(panel);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(Panel panelPruned, final BindingResult binding) {
        ModelAndView result;



        try {
            Panel panel = this.panelService.reconstructS(panelPruned,binding);

            if (binding.hasErrors()){
                result = this.createEditModelAndView(panelPruned);
            }
            else {
                this.panelService.save(panel);
                result = new ModelAndView("redirect:list.do");
            }
        } catch (final Throwable oops) {
            if (binding.hasErrors()){
                result = this.createEditModelAndView(panelPruned);
            }else{
                result = this.createEditModelAndView(panelPruned, "general.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView edit(Panel panel, HttpServletRequest request) {
        ModelAndView result;

        try {
            panelService.delete(panel);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(panel, "general.commit.error");
        }

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Panel panel) {
        ModelAndView result;

        result = this.createEditModelAndView(panel, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Panel panel, final String messageCode) {
        ModelAndView result;
        User user = userService.findByPrincipal();

        result = new ModelAndView("panel/edit");
        result.addObject("panel", panel);
        result.addObject("actionURI","panel/user/edit.do");

        return result;
    }
}

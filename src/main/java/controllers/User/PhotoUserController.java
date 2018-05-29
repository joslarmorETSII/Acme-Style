package controllers.User;

import controllers.AbstractController;
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
import services.PhotoService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/photo/user")
public class PhotoUserController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PanelService panelService;

    @Autowired
    private UserService userService;

    // Constructor -----------------------------------------

    public PhotoUserController() {
        super();
    }

    // Creation ------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Photo photo ;

        photo = this.photoService.create();

        result = this.createEditModelAndView(photo);

        return result;
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;

        Collection<Photo> photos= new ArrayList<Photo>();

        photos=this.photoService.findAll();

        result = new ModelAndView("panel/list");
        result.addObject("photos", photos);
        result.addObject("requestURI","panel/list.do");

        return result;

    }

    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int photoId) {
        final ModelAndView result;
        Photo photo;
        photo = this.photoService.findOneToEdit(photoId);
        Assert.notNull(photo);
        result = this.createEditModelAndView(photo);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Photo photo, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors())
            result = this.createEditModelAndView(photo);
        else
            try {
                    this.photoService.save(photo);
                    result = new ModelAndView("redirect: ../../photo/user/list.do");

            } catch (final Throwable oops) {
                result = this.createEditModelAndView(photo, "general.commit.error");
            }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int photoId) {

        ModelAndView result;
        Photo photo = photoService.findOne(photoId);
        try {
            photoService.delete(photo);
            result = new ModelAndView("redirect:../../panel/user/display.do?panelId="+photo.getPanel().getId());
        } catch (Throwable oops) {
            result = createEditModelAndView(photo, "general.commit.error");
        }

        return result;
    }



    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Photo photo) {
        ModelAndView result;

        result = this.createEditModelAndView(photo, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Photo photo, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("photo/edit");
        result.addObject("photo", photo);
        result.addObject("actionURI", "photo/user/edit.do");
        result.addObject("message", messageCode);
        result.addObject("cancelURI", "panel/user/list.do");

        return result;
    }
}

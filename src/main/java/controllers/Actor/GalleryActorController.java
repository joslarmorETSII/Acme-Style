package controllers.Actor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import controllers.AbstractController;
import domain.Actor;
import domain.Gallery;
import domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.GalleryService;
import services.ProfileService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/gallery/actor")
public class GalleryActorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ProfileService profileService;

    // Constructor -----------------------------------------

    public GalleryActorController() {
        super();
    }

    // Creation ---------------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Gallery gallery;


        gallery = galleryService.create();

        result = new ModelAndView("gallery/edit");
        result.addObject("gallery", gallery);
        result.addObject("requestURI","gallery/actor/edit.do");
        result.addObject("cancelURI","gallery/actor/list.do?profileId="+gallery.getProfile().getId());

        return result;
    }

    // Edit ------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int galleryId){
        ModelAndView result;
        Gallery gallery;

        gallery = galleryService.findOne(galleryId);
        Assert.isTrue(gallery.getProfile().getActor().equals(actorService.findByPrincipal()));
        result = new ModelAndView("gallery/edit");
        result.addObject("gallery",gallery);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "save")
    public ModelAndView edit(@Valid Gallery gallery, BindingResult binding){
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(gallery);
        } else
            try {
                galleryService.save(gallery);
                result = list(gallery.getProfile().getId());
            } catch (Throwable oops) {
                result = createEditModelAndView(gallery, "general.commit.error");
            }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int galleryId){
        ModelAndView result;
        Gallery gallery;

        gallery = galleryService.findOne(galleryId);
        Assert.isTrue(gallery.getProfile().getActor().equals(actorService.findByPrincipal()));
        galleryService.delete(gallery);
        result = new ModelAndView("redirect:list.do?profileId="+gallery.getProfile().getId());

        return result;
    }


    // Listing ----------------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam int profileId) {
        ModelAndView result;
        Profile profile;
        Actor actor;
        Collection<Gallery> galleries;
        Boolean owner;


        actor = actorService.findByPrincipal();
        profile = profileService.findOne(profileId);
        owner =actor.equals(profile.getActor());
        Assert.notNull(profile);
        galleries = profile.getGalleries();
        result = new ModelAndView("gallery/list");
        result.addObject("requestURI", "gallery/actor/list.do?profileId="+profileId);
        result.addObject("cancelURI", "welcome/index.do");
        result.addObject("galleries", galleries);
        result.addObject("owner", owner);
        result.addObject("profile", profile);


        return result;
    }
    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(Gallery gallery) {
        return createEditModelAndView(gallery, null);
    }

    protected ModelAndView createEditModelAndView(Gallery gallery ,  String text) {
        ModelAndView result;

        result = new ModelAndView("gallery/edit");
        result.addObject("gallery", gallery);
        result.addObject("message", text);
        result.addObject("requestURI", "gallery/actor/edit.do");
        result.addObject("cancelURI","gallery/actor/list.do?profileId="+gallery.getProfile().getId());

        return result;
    }
}

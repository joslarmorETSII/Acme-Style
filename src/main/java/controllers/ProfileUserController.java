package controllers;

import domain.Actor;
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
import services.ProfileService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/profile/actor")
public class ProfileUserController extends AbstractController{
    // Services --------------------------------------------

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ActorService actorService;

    // Constructor -----------------------------------------

    public ProfileUserController() {
        super();
    }



    // Edition ----------------------------------------------------------------

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display() {
        ModelAndView result;
        Profile profile;
        Actor actor;

        actor = actorService.findByPrincipal();
        profile = actor.getProfile();
        result = new ModelAndView("profile/display");
        result.addObject("profile",profile);
        result.addObject("actor",actor);
        result.addObject("followings_num",actor.getFollowings().size());
        result.addObject("followers_num",actor.getFollowers().size());

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int profileId) {
        ModelAndView result;
        Profile profile;

        profile = profileService.findOne(profileId);
        Assert.notNull(profile);
        result = this.createEditModelAndView(profile);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Profile profile, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors())
            result = this.createEditModelAndView(profile);
        else
            try {
                profileService.save(profile);
                result = new ModelAndView("redirect: /profile/actor/display.do");
            } catch ( Throwable oops) {
                result = this.createEditModelAndView(profile, "general.commit.error");
            }
        return result;
    }


    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(Profile profile) {
        ModelAndView result;

        result = this.createEditModelAndView(profile, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Profile profile, String text) {
        ModelAndView result;

        result = new ModelAndView("profile/edit");
        result.addObject("profile", profile);
        result.addObject("message", text);
        result.addObject("requestURI", "profile/actor/edit.do");

        return result;
    }

}

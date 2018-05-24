package controllers;


import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ProfileService;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController{
    // Services --------------------------------------------


    @Autowired
    private ActorService actorService;

    // Constructor -----------------------------------------


    public ActorController() { super(); }

    // List followers and followings ----------------------

    @RequestMapping(value = "/list-followers", method = RequestMethod.GET)
    public ModelAndView listFollowers() {
        ModelAndView result;
        Actor principal;

        principal = actorService.findByPrincipal();
        result = new ModelAndView("actor/followers");
        result.addObject("followers", principal.getFollowers());
        result.addObject("requestURI", "list-followers.do");

        return result;

    }

    @RequestMapping(value = "/list-following", method = RequestMethod.GET)
    public ModelAndView listFollowing() {
        ModelAndView result;
        Actor principal;

        principal = actorService.findByPrincipal();
        result = new ModelAndView("actor/followings");
        result.addObject("followings", principal.getFollowings());
        result.addObject("requestURI", "actor/list-following.do");

        return result;

    }
    // Follow & unfollow -----------------------------------------

    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public ModelAndView doFollow(@RequestParam int actorId) {
        ModelAndView result;
        Actor actor;

        actor = actorService.findOne(actorId);
        actorService.follow(actorId);
        result = new ModelAndView("redirect: ../profile/actor/view.do?profileId="+actor.getProfile().getId());

        return result;
    }


    @RequestMapping(value = "/unfollow", method = RequestMethod.GET)
    public ModelAndView doUnfollow(@RequestParam int actorId) {
        ModelAndView result;
        Actor actor;

        actor = actorService.findOne(actorId);
        actorService.unfollow(actorId);
        result = new ModelAndView("redirect: ../profile/actor/view.do?profileId="+actor.getProfile().getId());

        return result;
    }
}

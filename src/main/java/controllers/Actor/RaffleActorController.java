package controllers.Actor;

import com.sun.imageio.spi.RAFImageInputStreamSpi;
import controllers.AbstractController;
import domain.Actor;
import domain.Comment;
import domain.Post;
import domain.Raffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.CategoryService;
import services.PostService;
import services.RaffleService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/raffle/actor")
public class RaffleActorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private RaffleService raffleService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private PostService postService;

    // Constructor --------------------------------------------

    public RaffleActorController() {
        super();
    }

    // Creation ------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int postId) {
        ModelAndView result;
        Raffle raffle= null ;
        Post post = this.postService.findOne(postId);

        raffle = this.raffleService.create();
        raffle.setPost(post);
        result = this.createEditModelAndView(raffle);

        return result;
    }

    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int raffleId) {
        final ModelAndView result;
        Raffle raffle;
        raffle = this.raffleService.findOneToEdit(raffleId);
        Assert.notNull(raffle);
        result = this.createEditModelAndView(raffle);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(Raffle rafflePruned, final BindingResult binding) {
        ModelAndView result;
        Raffle aux;
        try {
            Raffle raffle = this.raffleService.reconstructS(rafflePruned,binding);

            if (binding.hasErrors()){
                result = this.createEditModelAndView(rafflePruned);
            }
            else {
                aux = this.raffleService.save(raffle);
                result = new ModelAndView("redirect:/raffle/actor/display.do?raffleId=" + aux.getId());
            }
        } catch (final Throwable oops) {
            if (binding.hasErrors()){
                result = this.createEditModelAndView(rafflePruned);
            }else{
                result = this.createEditModelAndView(rafflePruned, "general.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "delete")
    public ModelAndView edit(Raffle rafflePruned){
        ModelAndView result;

        Raffle raffle = this.raffleService.findOneToEdit(rafflePruned.getId());
        try{
            raffleService.delete(raffle);
            result = new ModelAndView("redirect:/post/actor/list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(raffle,"general.commit.error");
        }

        return result;
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam final int raffleId) {
        ModelAndView result;
        Raffle raffle;
        raffle = this.raffleService.findOne(raffleId);
        Actor actor = actorService.findByPrincipal();
        result = new ModelAndView("raffle/display");

        result.addObject("row", raffle);
        result.addObject("actor", actor);
        result.addObject("cancelURI", "post/actor/list.do");

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Raffle raffle) {
        ModelAndView result;

        result = this.createEditModelAndView(raffle, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Raffle raffle, final String messageCode) {
        ModelAndView result;
        result = new ModelAndView("raffle/edit");
        result.addObject("raffle", raffle);
        result.addObject("message", messageCode);
        result.addObject("actionURI","raffle/actor/edit.do");
        result.addObject("cancelURI", "welcome/index.do");

        return result;
    }
}

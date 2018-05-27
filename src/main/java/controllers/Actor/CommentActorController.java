package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Comment;
import domain.Post;
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
import services.CommentService;
import services.PostService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/comment/actor")
public class CommentActorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private ActorService actorService;

    // Constructor --------------------------------------------

    public CommentActorController() {
        super();
    }

    // Creation ------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int postId) {
        ModelAndView result;
        Comment comment;
        Post post = this.postService.findOne(postId);

        comment = this.commentService.create();
        comment.setPost(post);
        result = this.createEditModelAndView(comment);

        return result;
    }

    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int commentId) {
        final ModelAndView result;
        Comment comment;
        comment = this.commentService.findOne(commentId);
        Assert.notNull(comment);
        result = this.createEditModelAndView(comment);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(Comment commentPruned, final BindingResult binding) {
        ModelAndView result;

        try {
            Comment comment = this.commentService.reconstructS(commentPruned,binding);

            if (binding.hasErrors()){
                result = this.createEditModelAndView(commentPruned);
            }
            else {
                this.commentService.save(comment);
                result = new ModelAndView("redirect:/post/actor/list.do");
            }
        } catch (final Throwable oops) {
            if (binding.hasErrors()){
                result = this.createEditModelAndView(commentPruned);
            }else{
                result = this.createEditModelAndView(commentPruned, "general.commit.error");
            }
        }
        return result;
    }

    /*// Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        Actor actor;
        Collection<Comment> comments = new ArrayList<>();

        actor = actorService.findByPrincipal();
        comments = actor.getComments();

        result = new ModelAndView("comment/list");
        result.addObject("comments", comments);
        result.addObject("actor", actor);
        result.addObject("requestURI","comment/actor/list.do");
        result.addObject("cancelURI", "welcome/index.do");

        return result;

    }*/

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Comment comment) {
        ModelAndView result;

        result = this.createEditModelAndView(comment, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Comment comment, final String messageCode) {
        ModelAndView result;
        result = new ModelAndView("comment/edit");

        result.addObject("comment", comment);
        result.addObject("message", messageCode);
        result.addObject("actionURI","comment/actor/edit.do");
        result.addObject("cancelURI", "post/actor/list.do");

        return result;
    }
}

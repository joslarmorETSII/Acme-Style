package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Comment;
import domain.Post;
import domain.Servise;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/post/actor")
public class PostActorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private PostService postService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private CategoryService categoryService;

    // Constructor --------------------------------------------

    public PostActorController() {
        super();
    }

    // Creation ------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Post post= null ;

        post = this.postService.create();
        result = this.createEditModelAndView(post);

        return result;
    }

    // Listing -------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        Actor actor;
        Collection<Post> posts = new ArrayList<Post>();

        actor = actorService.findByPrincipal();
        posts = actor.getPosts();

        result = new ModelAndView("post/list");
        result.addObject("posts", posts);
        result.addObject("actor", actor);
        result.addObject("requestURI","post/actor/list.do");

        return result;

    }

    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int postId) {
        final ModelAndView result;
        Post post;
        post = this.postService.findOneToEdit(postId);
        Assert.notNull(post);
        result = this.createEditModelAndView(post);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(Post postPruned, final BindingResult binding) {
        ModelAndView result;

        try {
            Post post = this.postService.reconstructS(postPruned,binding);

            if (binding.hasErrors()){
                result = this.createEditModelAndView(postPruned);
            }
            else {
                this.postService.save(post);
                result = new ModelAndView("redirect:list.do");
            }
        } catch (final Throwable oops) {
            if (binding.hasErrors()){
                result = this.createEditModelAndView(postPruned);
            }else{
                result = this.createEditModelAndView(postPruned, "general.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,params = "delete")
    public ModelAndView edit(Post postPruned){
        ModelAndView result;

        Post post = this.postService.findOneToEdit(postPruned.getId());
        try{
            postService.delete(post);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(post,"general.commit.error");
        }

        return result;
    }

    // Like/Dislike/Heart ----------------------------------------------------------------

    @RequestMapping(value = "/lik", method = RequestMethod.GET)
    public ModelAndView lik(@RequestParam int postId){
        ModelAndView result;
        Post post= postService.findOne(postId);

        try{
            postService.likePost(post);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(post,"general.commit.error");
        }

        return result;
    }

    @RequestMapping(value = "/dislike", method = RequestMethod.GET)
    public ModelAndView dislike(@RequestParam int postId){
        ModelAndView result;
        Post post= postService.findOne(postId);

        try{
            postService.dislikePost(post);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(post,"general.commit.error");
        }

        return result;
    }

    @RequestMapping(value = "/heart", method = RequestMethod.GET)
    public ModelAndView heart(@RequestParam int postId){
        ModelAndView result;
        Post post= postService.findOne(postId);

        try{
            postService.heartPost(post);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createEditModelAndView(post,"general.commit.error");
        }

        return result;
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam final int postId) {
        ModelAndView result;
        Post post;
        post = this.postService.findOne(postId);
        Collection<Comment> comments = post.getComments();

        result = new ModelAndView("post/display");

        result.addObject("row", post);
        result.addObject("comments", comments);
        result.addObject("cancelURI", "post/actor/list.do");

        return result;
    }

    // Ancillary methods ------------------------------------------------------

    protected ModelAndView createEditModelAndView(final Post post) {
        ModelAndView result;

        result = this.createEditModelAndView(post, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final Post post, final String messageCode) {
        ModelAndView result;
        result = new ModelAndView("post/edit");
        result.addObject("allCategories", categoryService.findAll());
        result.addObject("post", post);
        result.addObject("message", messageCode);
        result.addObject("actionURI","post/actor/edit.do");
        result.addObject("cancelURI", "post/actor/list.do");

        return result;
    }
}

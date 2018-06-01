package controllers.Actor;

import controllers.AbstractController;
import domain.*;
import forms.SubscribeServiseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

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

    @Autowired
    private ActionService actionService;

    @Autowired
    private UserService userService;

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
        Collection<Panel> panels= new ArrayList<Panel>();
        Collection<Post> postRes = new ArrayList<Post>();

        actor = actorService.findByPrincipal();
        posts = actor.getPosts();

        postRes.addAll(this.actorService.postByFollowings(actor.getId()));
        postRes.addAll(posts);
        postRes.removeAll(this.postService.postFinalModeFalse());

        if(actorService.isUser()){
            panels = userService.findByPrincipal().getPanels();
        }
        result = new ModelAndView("post/list");
        result.addObject("posts", postRes);
        result.addObject("actor", actor);
        result.addObject("requestURI","post/actor/list.do");
        result.addObject("myPanels",panels);

        return result;

    }

    @RequestMapping(value = "/listRaffle", method = RequestMethod.GET)
    public ModelAndView listRaffle() {
        ModelAndView result;
        Actor actor;
        Collection<Post> posts = new ArrayList<Post>();
        Collection<Panel> panels= new ArrayList<Panel>();
        Collection<Post> postRes = new ArrayList<Post>();

        actor = actorService.findByPrincipal();
        posts = postService.postRaffleByActor(actor.getId());

        if(actorService.isUser()){
            panels = userService.findByPrincipal().getPanels();
        }
        result = new ModelAndView("post/listRaffle");
        result.addObject("posts", posts);
        result.addObject("actor", actor);
        result.addObject("requestURI","post/actor/listRaffle.do");
        result.addObject("myPanels",panels);

        return result;

    }

    //  Edition ----------------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int postId) {
        final ModelAndView result;
        Post post;
        post = this.postService.findOneToEdit(postId);
        Assert.notNull(post);

        Assert.isTrue(post.isRaffle(), "Can't edit a post");
        Assert.isTrue(!post.isFinalMode(), "The raffle is at final mode");

        result = this.createEditModelAndView(post);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(Post postPruned, final BindingResult binding) {
        ModelAndView result;
        if(postPruned.isRaffle()){
            postService.checkReward(postPruned.getReward(),binding);
            postService.checkEndDate(postPruned.getEndDate(), binding);
        }
        try {
            Post post = this.postService.reconstructS(postPruned,binding);

            if (binding.hasErrors()){
                result = this.createEditModelAndView(postPruned);
            }
            else {
                this.postService.save(post);
                if(post.isRaffle()){
                    result = new ModelAndView("redirect:listRaffle.do");
                }else{
                    result = new ModelAndView("redirect:list.do");
                }
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

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int postId){
        ModelAndView result;

        Post post = this.postService.findOneToEdit(postId);
        postService.delete(post);
        result = new ModelAndView("redirect:list.do");

        return result;
    }

    // Get winner ----------------------------------------------------------------

    @RequestMapping(value = "/getWinner", method = RequestMethod.GET)
    public ModelAndView getWinner(@RequestParam int postId){
        ModelAndView result;

        Post post = this.postService.findOneToEdit(postId);
        try{
            postService.getWinner(post);
            result = new ModelAndView("redirect:display.do?postId=" + postId);
        }catch (Throwable oops){
            if(oops.getMessage().contains("Index: 0, Size: 0" )) {
                result = display(postId);
                result.addObject("message", "general.commit.post.notWinner");
                post.setHasWinner(true);
                this.postService.save(post);
            }else {
                result = display(postId);
                result.addObject("message", "general.commit.error");
            }
        }
        return result;
    }

    // Like/Dislike/Heart ----------------------------------------------------------------

    @RequestMapping(value = "/lik", method = RequestMethod.GET)
    public ModelAndView lik(@RequestParam int postId){
        ModelAndView result;
        Post post= postService.findOne(postId);
        Actor actor = this.actorService.findByPrincipal();

        Assert.notNull(post);


        Action action = this.actionService.actionByActorAndPost(actor.getId(), postId);
        if(action == null){
            Action aux = this.actionService.create();
            aux.setPost(post);
            aux.setLik(true);
            this.actionService.save(aux);
            this.postService.likePost(post);
        }else {
            if(action.isLik()){
                action.setLik(false);
                this.actionService.save(action);
                this.postService.substractLikePost(post);
            }else if(action.isDislike()){
                action.setDislike(false);
                action.setLik(true);
                this.actionService.save(action);
                this.postService.substractDislikePost(post);
                this.postService.likePost(post);
            }else if(action.isHeart()){
                action.setHeart(false);
                action.setLik(true);
                this.actionService.save(action);
                this.postService.substractHeartPost(post);
                this.postService.likePost(post);
            }
            else{
                action.setLik(true);
                this.actionService.save(action);
                this.postService.likePost(post);
            }
        }try{
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
        Actor actor = this.actorService.findByPrincipal();

        Assert.notNull(post);

        Action action = this.actionService.actionByActorAndPost(actor.getId(), postId);
        if(action == null){
            Action aux = this.actionService.create();
            aux.setPost(post);
            aux.setDislike(true);
            this.actionService.save(aux);
            this.postService.dislikePost(post);
        }else {
            if(action.isLik()){
                action.setLik(false);
                action.setDislike(true);
                this.actionService.save(action);
                this.postService.substractLikePost(post);
                this.postService.dislikePost(post);
            }else if(action.isDislike()){
                action.setDislike(false);
                this.actionService.save(action);
                this.postService.substractDislikePost(post);
            }else if(action.isHeart()){
                action.setHeart(false);
                action.setDislike(true);
                this.actionService.save(action);
                this.postService.substractHeartPost(post);
                this.postService.dislikePost(post);
            }
            else{
                action.setDislike(true);
                this.actionService.save(action);
                this.postService.dislikePost(post);
            }

        }try{
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
        Actor actor = this.actorService.findByPrincipal();

        Action action = this.actionService.actionByActorAndPost(actor.getId(), postId);
        if(action == null){
            Action aux = this.actionService.create();
            aux.setPost(post);
            aux.setHeart(true);
            this.actionService.save(aux);
            this.postService.heartPost(post);
        }else {
            if (action.isLik()) {
                action.setLik(false);
                action.setHeart(true);
                this.actionService.save(action);
                this.postService.substractLikePost(post);
                this.postService.heartPost(post);
            } else if (action.isDislike()) {
                action.setDislike(false);
                action.setHeart(true);
                this.actionService.save(action);
                this.postService.substractDislikePost(post);
                this.postService.heartPost(post);
            } else if (action.isHeart()) {
                action.setHeart(false);
                this.actionService.save(action);
                this.postService.substractHeartPost(post);
            } else {
                action.setHeart(true);
                this.actionService.save(action);
                this.postService.heartPost(post);
            }
        }
        try{
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
        result.addObject("actor", actorService.findByPrincipal());
        if(post.isRaffle())
            result.addObject("hasFinished", post.getEndDate().before(new Date()));
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

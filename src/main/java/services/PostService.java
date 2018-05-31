package services;

import domain.*;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.PostRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class PostService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private PostRepository postRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private Validator validator;

    @Autowired
    private ActionService actionService;

    @Autowired
    private CommentService commentService;

    // Constructors -----------------------------------------------------------

    public PostService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Post create(){
        Post result;

        result = new Post();
        result.setActor(actorService.findByPrincipal());
        result.setCategories(new ArrayList<Category>());
        result.setComments(new ArrayList<Comment>());
        result.setActions(new ArrayList<Action>());

        return result;
    }

    public Post findOne(int id){
        return postRepository.findOne(id);
    }

    public Post findOneToEdit(int id){
        Post res = postRepository.findOne(id);
        Actor actor = actorService.findByPrincipal();

        Assert.isTrue(res.getActor().equals(actor), "Not the creator of this Post");

        return res;
    }

    public Collection<Post> findAll(){
        return postRepository.findAll();
    }

    public void delete(Post post){
        Assert.notNull(post);
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()) || actorService.checkRole(Authority.ADMINISTRATOR));

        post.setCategories(new ArrayList<Category>());
        for(Comment c : post.getComments()){
            Actor a = c.getActor();
            a.setComments(new ArrayList<Comment>());
            this.actorService.save(a);
        }
        this.commentService.deleteAll(post);

        //TODO: Mirar en los requisitos la condicion de raffle de borrado.

        for(Action a : post.getActions()){
            Actor aux = a.getActor();
            aux.getActions().remove(a);
            this.actorService.save(aux);

        }
        actionService.deleteAll(post.getActions());
        post.setActions(new ArrayList<Action>());

        postRepository.delete(post);
    }

    public Post save(Post post){
        Assert.notNull(post);
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()));

        post.setMoment(new Date(System.currentTimeMillis() - 1000));

        Post res = postRepository.save(post);

        return res;
    }

    // Other business methods -------------------------------------------------

    public Post reconstructS(final Post postPruned, final BindingResult binding) {
        Post res;

        if (postPruned.getId() == 0) {
            res = this.create();
        } else {
            res = this.findOne(postPruned.getId());
        }

        res.setTitle(postPruned.getTitle());
        res.setDescription(postPruned.getDescription());
        res.setPicture(postPruned.getPicture());
        res.setCategories(postPruned.getCategories());


        this.validator.validate(res,binding);

        return res;
    }

    public void likePost(Post post){
        post.setLik(post.getLik() + 1);
        this.postRepository.save(post);
    }


    public void substractLikePost(Post post){
        post.setLik(post.getLik() - 1);
        this.postRepository.save(post);
    }

    public void dislikePost(Post post){
        post.setDislike(post.getDislike() + 1);
        this.postRepository.save(post);
    }

    public void substractDislikePost(Post post){
        post.setDislike(post.getDislike() - 1);
        this.postRepository.save(post);
    }

    public void heartPost(Post post){
        post.setHeart(post.getHeart() + 1);
        this.postRepository.save(post);
    }

    public void substractHeartPost(Post post){
        post.setHeart(post.getHeart() - 1);
        this.postRepository.save(post);
    }
}

package services;

import domain.Category;
import domain.Comment;
import domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.PostRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class PostService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private PostRepository postRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

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

        return result;
    }

    public Post findOne(int id){
        return postRepository.findOne(id);
    }

    public Collection<Post> findAll(){
        return postRepository.findAll();
    }

    public void delete(Post post){
        Assert.notNull(post);
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()) || actorService.checkRole(Authority.ADMINISTRATOR));
        postRepository.delete(post);
    }

    public Post save(Post post){
        Assert.notNull(post);
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()));
        return postRepository.save(post);
    }

    // Other business methods -------------------------------------------------


}

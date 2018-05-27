package services;

import domain.Post;
import domain.Raffle;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.RaffleRepository;
import security.Authority;
import sun.tools.tree.VarDeclarationStatement;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class RaffleService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private RaffleRepository raffleRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private Validator validator;

    @Autowired
    private ActorService actorService;

    @Autowired
    private PostService postService;

    // Constructors -----------------------------------------------------------

    public RaffleService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Raffle create(){
        Raffle result;

        result = new Raffle();


        return result;
    }

    public Raffle findOne(int id){
        return raffleRepository.findOne(id);
    }

    public Raffle findOneToEdit(int id){
        Raffle raffle = raffleRepository.findOne(id);

        Assert.isTrue(raffle.getPost().getActor().equals(actorService.findByPrincipal()));

        return raffle;
    }

    public Collection<Raffle> findAll(){
        return raffleRepository.findAll();
    }

    public void delete(Raffle raffle){
        Assert.notNull(raffle);
        Post post= raffle.getPost();
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()) || actorService.checkRole(Authority.ADMINISTRATOR));
        post.setRaffle(null);
        raffle.setPost(null);
        this.postService.save(post);
        raffleRepository.delete(raffle);
    }

    public Raffle save(Raffle raffle){
        Assert.notNull(raffle);
        Raffle res;
        Post post= raffle.getPost();
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()));
        res = raffleRepository.save(raffle);
        post.setRaffle(res);
        this.postService.save(post);
        return res;
    }

    // Other business methods -------------------------------------------------

    public Raffle reconstructS(final Raffle rafflePruned, final BindingResult binding) {
        Raffle res;

        if (rafflePruned.getId() == 0) {
            res = this.create();
        } else {
            res = this.findOne(rafflePruned.getId());
        }

        res.setTitle(rafflePruned.getTitle());
        res.setDescription(rafflePruned.getDescription());
        res.setEndDate(rafflePruned.getEndDate());
        res.setReward(rafflePruned.getReward());
        res.setPost(rafflePruned.getPost());

        this.validator.validate(res,binding);

        return res;
    }

}

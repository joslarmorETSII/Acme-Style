package services;

import domain.Post;
import domain.Raffle;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RaffleRepository;
import security.Authority;

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
    private UserService userService;

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public RaffleService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Raffle create(){
        Raffle result;

        result = new Raffle();
        result.setUsers(new ArrayList<User>());

        return result;
    }

    public Raffle findOne(int id){
        return raffleRepository.findOne(id);
    }

    public Collection<Raffle> findAll(){
        return raffleRepository.findAll();
    }

    public void delete(Raffle raffle){
        Assert.notNull(raffle);
        Post post= raffle.getPost();
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()) || actorService.checkRole(Authority.ADMINISTRATOR));
        raffleRepository.delete(raffle);
    }

    public Raffle save(Raffle raffle){
        Assert.notNull(raffle);
        Post post= raffle.getPost();
        Assert.isTrue(actorService.findByPrincipal().equals(post.getActor()));
        return raffleRepository.save(raffle);
    }

    // Other business methods -------------------------------------------------

}

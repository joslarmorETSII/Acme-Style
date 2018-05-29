package services;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActionRepository;
import repositories.SubscriptionRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class ActionService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private ActionRepository actionRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private PostService postService;

    // Constructors -----------------------------------------------------------

    public ActionService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Action create(){
        Action res;

        res = new Action();
        res.setActor(actorService.findByPrincipal());

        return res;
    }

    public Action findOne(int id){
        return actionRepository.findOne(id);
    }

    public Collection<Action> findAll(){

        return actionRepository.findAll();
    }

    public void delete(Action action){

        actionRepository.delete(action);
    }

    public void deleteAll(Collection<Action> actions){
        actionRepository.delete(actions);
    }

    public Action save(Action action){
        Actor actor = actorService.findByPrincipal();
        return actionRepository.save(action);
    }

    public void flush() {
        actionRepository.flush();
    }

    // Other business methods -------------------------------------------------

    //public Action actionByActorAndPost(int actorId,int postId){
    //    return actionRepository.actionByActorAndPost(actorId,postId);
    //}

    //public Collection<Post> postsActionPerActor(int actorId){
    //    return actionRepository.postsActionPerActor(actorId);
    //}

}

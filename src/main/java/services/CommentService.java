package services;

import domain.Actor;
import domain.Answer;
import domain.Comment;
import domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.ActorRepository;
import repositories.CommentRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CommentService {


    // Managed repository -----------------------------------------------------

    @Autowired
    private CommentRepository commentRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private Validator validator;

    // Constructors -----------------------------------------------------------

    public CommentService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Comment create(){
        Comment comment;
        Actor actor = actorService.findByPrincipal();

        comment = new Comment();

        comment.setActor(actor);
        comment.setMoment(new Date(System.currentTimeMillis() - 1000));
        actor.getComments().add(comment);

        return comment;
    }

    public Comment save(Comment comment){
        Assert.notNull(comment);
        Assert.isTrue(actorService.isManager() || actorService.isStylist() || actorService.isPhotographer()
        || actorService.isMakeUpArtist() || actorService.isUser());

        comment.setMoment(new Date(System.currentTimeMillis() - 1000));

        return commentRepository.save(comment);
    }

    public void delete(Comment comment){
        commentRepository.delete(comment);
    }

    public Comment findOne(int id){
        return commentRepository.findOne(id);
    }

    public Collection<Comment> findAll(){
        return commentRepository.findAll();
    }

    // Other business methods -------------------------------------------------

    public Comment reconstructS(final Comment commentPruned, final BindingResult binding) {
        Comment res;

        if (commentPruned.getId() == 0) {
            res = this.create();
        } else {
            res = this.findOne(commentPruned.getId());
        }

        res.setText(commentPruned.getText());
        res.setMoment(commentPruned.getMoment());
        res.setActor(commentPruned.getActor());
        res.setPost(commentPruned.getPost());

        this.validator.validate(res,binding);

        return res;
    }

}

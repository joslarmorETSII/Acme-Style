package services;

import domain.Answer;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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

    // Constructors -----------------------------------------------------------

    public CommentService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Comment create(){
        Comment comment;

        comment = new Comment();
        comment.setActor(actorService.findByPrincipal());

        return comment;
    }

    public Comment save(Comment comment){
        Assert.notNull(comment);
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
}

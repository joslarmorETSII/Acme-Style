package services;

import domain.Answer;
import domain.Artist;
import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class QuestionService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ActorService    actorService;

    // Supporting services ----------------------------------------------------


    // Constructors -----------------------------------------------------------

    public QuestionService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Question create(){
        Question question;

        question = new Question();
        question.setAnswers(new ArrayList<Answer>());

        return question;
    }

    public Question save(Question question){
        Assert.notNull(question);
        question.setMoment(new Date(System.currentTimeMillis() - 1000));
        return questionRepository.save(question);
    }

    public void delete(Question question){
        Assert.isTrue(actorService.isAdministrator());
        answerService.deleteAll(question.getAnswers());
        questionRepository.delete(question);
    }

    public void deleteAll(Collection<Question> questions){
        for(Question q:questions){
            if(q.getAnswers()!=null)
                answerService.deleteAll(q.getAnswers());
            q.setAnswers(new ArrayList<Answer>());
            delete(q);
        }
    }

    public Question findOne(int id){
        return questionRepository.findOne(id);
    }

    public Collection<Question> findAll(){
        return questionRepository.findAll();
    }

    public Question findOneToEdit(int questionId) {
        Question question;
        Artist principal;

        principal = artistService.findByPrincipal();
        question = findOne(questionId);
        Assert.isTrue(question.getServise().getCreator().equals(principal),"Not the owner");
        return question;
    }



    // Other business methods -------------------------------------------------

    public void flush() {
        questionRepository.flush();
    }
}

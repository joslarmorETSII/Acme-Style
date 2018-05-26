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
        question.setMoment(new Date());
        return questionRepository.save(question);
    }

    public void delete(Question question){
        questionRepository.delete(question);
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
}

package services;

import domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AnswerRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class AnswerService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ActorService actorService;

    // Supporting services ----------------------------------------------------


    // Constructors -----------------------------------------------------------

    public AnswerService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Answer create(){
        Answer answer;

        answer = new Answer();

        return answer;
    }

    public Answer save(Answer answer){
        Assert.notNull(answer);
        answer.setMoment(new Date(System.currentTimeMillis() - 1000));
        return answerRepository.save(answer);
    }

    public void delete(Answer answer){
        Assert.isTrue(actorService.isAdministrator());
        answerRepository.delete(answer);
    }

    public void deleteAll(Collection<Answer> answers){
        answerRepository.delete(answers);
    }

    public Answer findOne(int id){
        return answerRepository.findOne(id);
    }

    public Collection<Answer> findAll(){
        return answerRepository.findAll();
    }

    public void flush() {
        answerRepository.flush();
    }

    // Other business methods -------------------------------------------------
}

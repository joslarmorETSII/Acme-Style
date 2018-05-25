package services;


import domain.Feedback;
import domain.Subscription;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.FeedbackRepository;
import repositories.SubscriptionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class FeedbackService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserService userService;

    @Autowired
    private ServiseService serviseService;

    @Autowired
    private SubscribeService subscribeService;

    // Constructors -----------------------------------------------------------

    public FeedbackService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Feedback create(){
        Feedback res;
        Collection<Integer> listpoints= new ArrayList<Integer>();
        res = new Feedback();
        res.setUser(userService.findByPrincipal());


        return res;
    }

    public Feedback findOne(int id){
        return feedbackRepository.findOne(id);
    }

    public Collection<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    public void delete(Feedback subscription){
        feedbackRepository.delete(subscription);
    }

    public Feedback save(Feedback feedback){
        User principal;

        principal = userService.findByPrincipal();
        Assert.notNull(subscribeService.subscriptionByUserAndService(principal.getId(),feedback.getServise().getId()),"not subscribed ");
        Collection<Integer> lisPoints= listPoints();


        return feedbackRepository.save(feedback);
    }

    public void deleteAll(Collection<Feedback> feedbacks){
        feedbackRepository.delete(feedbacks);
    }

    public Feedback feedbackByUserAndService(int userId, int serviseId){
        return feedbackRepository.feedbackByUserAndService(userId,serviseId);
    }

    public Collection<Integer> listPoints(){
        Collection<Integer> listPoints= new ArrayList<Integer>();
        Integer i;
        for(i=0;i<=5;i++){
            listPoints.add(i);
        }

        return listPoints;
    }

    public void flush() {
        feedbackRepository.flush();
    }

    // Other business methods -------------------------------------------------
}

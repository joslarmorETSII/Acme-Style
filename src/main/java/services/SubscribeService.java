package services;

import domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.SubscriptionRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class SubscribeService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserService userService;

    @Autowired
    private ServiseService serviseService;

    // Constructors -----------------------------------------------------------

    public SubscribeService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Subscription create(){
        Subscription res;

        res = new Subscription();
        res.setUser(userService.findByPrincipal());

        return res;
    }

    public Subscription findOne(int id){
        return subscriptionRepository.findOne(id);
    }

    public Collection<Subscription> findAll(){
        return subscriptionRepository.findAll();
    }

    public void delete(Subscription subscription){
        subscriptionRepository.delete(subscription);
    }

    public Subscription save(Subscription subscription){
        Assert.isTrue(subscription.getService().getPublicationDate().after(new Date()),"Service not published yet");
        subscription.setMoment(new Date());
        return subscriptionRepository.save(subscription);
    }

    public void flush() {
        subscriptionRepository.flush();
    }

    // Other business methods -------------------------------------------------

    public Subscription subscriptionByUserAndService(int userId,int serviceId){
        return subscriptionRepository.subscriptionByUserAndService(userId,serviceId);
    }

}

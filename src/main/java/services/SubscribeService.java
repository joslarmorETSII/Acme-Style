package services;

import domain.Servise;
import domain.Subscription;
import domain.User;
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

    public void deleteAll(Collection<Subscription> subscriptions){
        subscriptionRepository.delete(subscriptions);
    }

    public Subscription save(Subscription subscription){
        subscription.setMoment(new Date(System.currentTimeMillis() - 1000));
        User user = userService.findByPrincipal();
         return subscriptionRepository.save(subscription);
    }

    public void flush() {
        subscriptionRepository.flush();
    }

    // Other business methods -------------------------------------------------

    public Subscription subscriptionByUserAndService(int userId,int serviseId){
        return subscriptionRepository.subscriptionByUserAndService(userId,serviseId);
    }

    public Collection<Servise> servisesSubscribedPerUser(int userId){
        return subscriptionRepository.servisesSubscribedPerUser(userId);
    }

}

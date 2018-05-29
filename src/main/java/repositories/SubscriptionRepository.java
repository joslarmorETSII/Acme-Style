package repositories;

import domain.Servise;
import domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {

    @Query("select s from Subscription s where s.user.id = ?1 and s.servise.id =?2")
    Subscription subscriptionByUserAndService(int userId,int serviseId);

    @Query("select s.servise from Subscription s where s.user.id=?1")
    Collection<Servise> servisesSubscribedPerUser(int userId);

}

package repositories;

import domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {


    @Query("select s from Subscription s where s.user.id = ?1 and s.service.id =?2")
    Subscription subscriptionByUserAndService(int userId,int serviceId);
}

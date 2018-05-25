package repositories;

import domain.Feedback;
import domain.Servise;
import domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{

    @Query("select f from Feedback f where f.user.id = ?1 and f.servise.id =?2")
    Feedback feedbackByUserAndService(int userId, int serviseId);
}

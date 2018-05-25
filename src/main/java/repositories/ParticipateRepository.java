package repositories;

import domain.Event;
import domain.Participate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate,Integer>{

    @Query("select p from Participate p where p.user.id = ?1 and p.event.id =?2")
    Participate participateByUserAndEvent(int actorId, int eventId);
}

package repositories;

import domain.Event;
import domain.Servise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer>{

    @Query("select p.event from Participate p WHERE p.user.id =?1")
    Collection<Event> participatedEvents(int id);

    //Search
    @Query("select e from Event e where e.title like %?1%")
    Collection<Event> searchEventsPerKeyword(String title);

}

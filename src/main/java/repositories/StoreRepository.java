package repositories;

import domain.Event;
import domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer>{

    @Query("select e from Event e where e.store.id = ?1")
    Collection<Event> eventsOfaStore(int storeId );
}

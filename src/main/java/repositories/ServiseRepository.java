package repositories;

import domain.Servise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ServiseRepository  extends JpaRepository<Servise,Integer>{
    @Query("select s from Servise s where s.creator.id =?1")
    Collection<Servise> servisesPerCreator(int creatorId);

    @Query("select s from Servise s where s.taboo =true")
    Collection<Servise> servisesTaboo();

    //Search
    @Query("select s from Servise s where (s.title like %?1%  or s.description like %?2%)")
    Collection<Servise> searchServisesPerKeyword(String title, String description);
}

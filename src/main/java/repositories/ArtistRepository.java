package repositories;

import domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {

    @Query("select a from Artist a where a.userAccount.id=?1")
    Artist findByUserAccountId(int userAccountId);
}

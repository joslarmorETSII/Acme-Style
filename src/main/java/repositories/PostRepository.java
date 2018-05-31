package repositories;

import domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository  extends JpaRepository<Post,Integer>{

    @Query("select p from Post p where p.finalMode=false")
    Collection<Post> postFinalModeFalse();

    @Query("select p from Post p where p.actor.id = ?1 and p.finalMode = false ")
    Collection<Post> postRaffleByActor(int actorId);
}

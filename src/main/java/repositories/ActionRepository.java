package repositories;

import domain.Action;
import domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActionRepository extends JpaRepository<Action,Integer> {

    //@Query("select a from Action a where a.actor.id = ?1 and a.post.id =?2")
    //Action actionByActorAndPost(int actorId, int postId);

    //@Query("select a.post from Action a where a.actor.id=?1")
    //Collection<Post> postsActionPerActor(int actorId);

}

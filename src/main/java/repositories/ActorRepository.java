package repositories;

import domain.Actor;
import domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{

    @Query("select a from Actor a where a.userAccount.id = ?1")
    Actor findByUserAccountId(int userAccountId);

    @Query("select aux.posts from Actor a join a.followings aux where a.id = ?1")
    Collection<Post> postByFollowings(int actorId);
}

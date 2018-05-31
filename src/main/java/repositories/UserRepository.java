package repositories;

import domain.Actor;
import domain.Servise;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.userAccount.id = ?1")
    User findByUserAccountId(int userAccountId);

    //Search
    @Query("select a from Actor a where (a.name like %?1%  or a.surname like %?1% or a.email like %?1%)")
    Collection<Actor> searchUsersPerKeyword(String keyword);
}

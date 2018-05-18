package repositories;

import domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer>{

    @Query("select a from Manager a where a.userAccount.id=?1")
    Manager findByUserAccountId(int userAccountId);
}

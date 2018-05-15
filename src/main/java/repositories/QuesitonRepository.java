package repositories;

import domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuesitonRepository extends JpaRepository<Question,Integer>{
}

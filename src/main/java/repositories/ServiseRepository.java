package repositories;

import domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiseRepository  extends JpaRepository<Service,Integer>{
}

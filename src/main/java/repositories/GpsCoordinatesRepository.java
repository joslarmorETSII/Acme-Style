package repositories;

import domain.GpsCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpsCoordinatesRepository extends JpaRepository<GpsCoordinates,Integer>{
}

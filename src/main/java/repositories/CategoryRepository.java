package repositories;

import domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Query("select distinct p.categories from Post p")
    Collection<Category> categoriesAssociated();
}

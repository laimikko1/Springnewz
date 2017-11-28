package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uutiset.wepauutiset.domain.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {
}

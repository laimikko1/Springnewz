package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}

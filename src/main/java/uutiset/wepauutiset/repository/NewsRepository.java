package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.wepauutiset.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}

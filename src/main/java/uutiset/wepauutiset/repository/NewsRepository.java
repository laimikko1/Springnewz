package uutiset.wepauutiset.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uutiset.wepauutiset.domain.News;

import java.util.List;


public interface NewsRepository extends JpaRepository<News, Long> {
}

package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.wepauutiset.domain.NewsClick;

public interface NewsClickRepository extends JpaRepository<NewsClick, Long> {


}

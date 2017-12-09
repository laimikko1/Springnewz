package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.NewsObject;


public interface NewsObjectRepository extends JpaRepository<NewsObject, Long> {
    NewsObject findByNews(News news);
}

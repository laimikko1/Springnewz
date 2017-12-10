package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.NewsClick;

import java.util.List;

public interface NewsClickRepository extends JpaRepository<NewsClick, Long> {

    public List<NewsClick> findAllByNews(News news);
}

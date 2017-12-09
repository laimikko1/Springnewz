package uutiset.wepauutiset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.News;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT * FROM news, news_categories, " +
            "category WHERE news.id = category_news_id " +
            "AND categories_id = category.id AND category.id = ?1 " +
            "ORDER BY news.publishdate ASC", nativeQuery = true)
    public List<News> findByCategory(Long categoryId);

}

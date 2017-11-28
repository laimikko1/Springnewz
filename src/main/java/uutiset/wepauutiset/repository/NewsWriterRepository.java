package uutiset.wepauutiset.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uutiset.wepauutiset.domain.NewsWriter;

public interface NewsWriterRepository extends JpaRepository<NewsWriter, Long> {
}

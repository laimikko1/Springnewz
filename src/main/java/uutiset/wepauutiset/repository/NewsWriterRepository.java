package uutiset.wepauutiset.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.wepauutiset.domain.Newswriter;

public interface NewsWriterRepository extends JpaRepository<Newswriter, Long> {
    Newswriter findByName(String name);
}

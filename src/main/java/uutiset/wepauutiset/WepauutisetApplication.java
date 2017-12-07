package uutiset.wepauutiset;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;

@SpringBootApplication
public class WepauutisetApplication {

    @Autowired
    private NewsWriterRepository newsWriterRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WepauutisetApplication.class, args);


    }
//
    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            Newswriter nw = new Newswriter();
            nw.setName("Mikko");
            nw.setPassword("mikko");
            Category c = new Category();
            c.setName("Kategoria");
            newsWriterRepository.save(nw);
            categoryRepository.save(c);
        };
    }


}



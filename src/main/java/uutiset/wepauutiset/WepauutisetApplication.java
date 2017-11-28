package uutiset.wepauutiset;

import org.h2.tools.RunScript;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.NewsWriter;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

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
            NewsWriter nw = new NewsWriter();
            nw.setName("Mikko");
            Category c = new Category();
            c.setName("Kategoria");
            newsWriterRepository.save(nw);
            categoryRepository.save(c);
        };
    }


}



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
import uutiset.wepauutiset.service.UserService;

@SpringBootApplication
public class WepauutisetApplication {

    @Autowired
    private NewsWriterRepository newsWriterRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WepauutisetApplication.class, args);


    }

//    //
    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            Category t = new Category();
            t.setName("Talous");
            t.setPinned(false);
            Category c = new Category();
            c.setName("Urheilu");
            t.setPinned(false);
            newsWriterRepository.save(userService.createUser("mikko", "salasana"));
            categoryRepository.save(t);
            categoryRepository.save(c);
        };
    }


}



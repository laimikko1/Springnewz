package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;

@Controller
public class CategoryAndWriterController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String category) {
        Category c = new Category();
        c.setName(category);
        c.setPinnedToMenu(false);
        categoryRepository.save(c);

        return "redirect:/";
    }

    @Autowired
    private NewsWriterRepository newsWriterRepository;


    @PostMapping("/addWriter")
    public String addWriter(@RequestParam String name) {
        Newswriter nw = new Newswriter();
        nw.setName(name);
        this.newsWriterRepository.save(nw);
        return "redirect:/";
    }
}

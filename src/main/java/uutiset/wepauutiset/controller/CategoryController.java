package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.repository.CategoryRepository;

public class CategoryController {

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

    @GetMapping("/categories")
    public String getCategories() {
        return "null";
    }

}


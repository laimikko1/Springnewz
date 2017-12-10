package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uutiset.wepauutiset.domain.Category;
import uutiset.wepauutiset.domain.Newswriter;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;
import uutiset.wepauutiset.service.NewsFinderService;
import uutiset.wepauutiset.service.ValidatorService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryAndWriterController {

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private NewsFinderService newsFinderService;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsWriterRepository newsWriterRepository;

    @Transactional
    @GetMapping("/modify")
    public String modifyCategories(Model model) {
        model.addAttribute("pinnedCategories", categoryRepository.findByPinned(true));
        model.addAttribute("notPinnedCategories", categoryRepository.findByPinned(false));

        model.addAttribute("newest", newsFinderService.findNewest());
        model.addAttribute("mostPopular", newsFinderService.findMostPopular(newsRepository.findAll()));
        model.addAttribute("navbarCategories", categoryRepository.findByPinned(true));


        return "modifyNavbar";
    }


    @GetMapping("/addCategoryOrWriter")
    public String getAddCategory(@ModelAttribute Category category, @ModelAttribute Newswriter newswriter, Model model) {
        model.addAttribute("newest", newsFinderService.findNewest());
        model.addAttribute("mostPopular", newsFinderService.findMostPopular(newsRepository.findAll()));
        model.addAttribute("navbarCategories", categoryRepository.findByPinned(new Boolean(true)));
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult,
                              @ModelAttribute Newswriter newswriter, Model model,
                              RedirectAttributes rel) {
        if (bindingResult.hasErrors()) {
            returnModelInfoOnError(bindingResult, model);
            return "addCategory";
        }


        categoryRepository.save(category);
        rel.addFlashAttribute("messages", "Category added!");

        return "redirect:/";

    }

    @PostMapping("/addNewswriter")
    public String addCategory(@Valid @ModelAttribute Newswriter newswriter, BindingResult bindingResult,
                              @ModelAttribute Category category, Model model,
                              RedirectAttributes rel) {
        if (bindingResult.hasErrors()) {
            returnModelInfoOnError(bindingResult, model);
            return "addCategory";
        }

        newsWriterRepository.save(newswriter);
        rel.addFlashAttribute("messages", "New newswriter added!");
        return "redirect:/";
    }


    @PostMapping("/modify/{param}")
    public String modifyCategories(@RequestParam(required = false) List<String> categories,
                                   @PathVariable String param) throws Exception {
        boolean p = true;
        if (categories != null) {
            if (!param.equals("t")) {
                p = false;
            }

            for (String s : categories) {
                Category c = categoryRepository.findByName(s);
                c.setPinned(p);
                categoryRepository.save(c);
            }
        }

        return "redirect:/";
    }


    private void returnModelInfoOnError(BindingResult bindingResult, Model model) {
        model.addAttribute("errors", validatorService.getErrorMessages(bindingResult));
        model.addAttribute("newest", newsFinderService.findNewest());
        model.addAttribute("mostPopular", newsFinderService.findMostPopular(newsRepository.findAll()));
        model.addAttribute("navbarCategories", categoryRepository.findByPinned(new Boolean(true)));
    }

}

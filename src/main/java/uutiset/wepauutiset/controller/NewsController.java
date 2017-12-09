package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uutiset.wepauutiset.domain.*;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsObjectRepository;
import uutiset.wepauutiset.repository.NewsRepository;
import uutiset.wepauutiset.repository.NewsWriterRepository;
import uutiset.wepauutiset.service.NewsFinderService;
import uutiset.wepauutiset.service.NewsValidatorService;
import uutiset.wepauutiset.service.SecurityService;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsObjectRepository newsObjectRepository;

    @Autowired
    private NewsWriterRepository newsWriterRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsFinderService newsFinderService;

    @Autowired
    private NewsValidatorService newsValidatorService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("news", newsFinderService.findNewest());
        addAsideListNewsAndNavbar(model);
        return "index";

    }

    @Transactional
    @GetMapping("/newsStory/{newsId}")
    public String showOne(Model model, @PathVariable Long newsId) throws Throwable {
        News news = newsRepository.getOne(newsId);
        news.addLick();
        newsRepository.save(news);
        model.addAttribute("n", news);
        model.addAttribute("writers", newsWriterRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        addAsideListNewsAndNavbar(model);

        return "newsStory";
    }

    @GetMapping("/news/edit/{newsId}")
    public String view(Model model, @PathVariable Long newsId) throws Throwable {
        if (!model.asMap().containsKey("news")) {
            News n = newsRepository.getOne(newsId);
            n.setEditId(newsId);
            model.addAttribute("news", n);
        }

        addAsideListNewsAndNavbar(model);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("writers", newsWriterRepository.findAll());
        return "modifyNewsstory";
    }


    @GetMapping("/news/{category}")
    public String findAllByCategory(Model model, @PathVariable String category) throws Throwable {
        Category c = categoryRepository.findByName(category);
        model.addAttribute("news", newsFinderService.findAllByCategory(c.getId()));
        addAsideListNewsAndNavbar(model);
        return "newsCategoryPage";
    }

    @GetMapping("/add")
    public String addNew(@ModelAttribute News news, BindingResult bindingResult, Model model) {
        addAsideListNewsAndNavbar(model);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
        }
        model.addAttribute("writers", newsWriterRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("navbarCategories", categoryRepository.findByPinned(new Boolean(true)));

        if (!model.asMap().containsKey("news")) {
            model.addAttribute("news", new News());
        }

        return "addNews";
    }

    @PostMapping("news/edit")
    public String modifyNews(@Valid @ModelAttribute News news, BindingResult bindingResult, RedirectAttributes rel, @RequestParam("newsObject")
            MultipartFile newsObject, @RequestParam Long editId) throws IOException {
        // TOOOODELLA huono viritelmä, mutta en keksinyt miten @ModelAttributen ja olemassaolevan olion saa juttelemaan
        // keskenään mitenkään erityisen fiksusti, joten tein purkkamaisen redirectin ja talletan sitten viestit siihen

        if (securityService.checkCredentials()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            news.setEditId(editId);
            rel.addFlashAttribute("errors", newsValidatorService.getErrorMessages(bindingResult));
            rel.addFlashAttribute("news", news);
            return "redirect:/news/edit/" + editId;
        }

        // En myöskään tajunnut miten saan nuo kentät kuten clicks, publishdate jne Modelattributeen mitenkään nätisti,
        // joten lisäsilen vain validoidut tiedot olemassaolevaan objektiin, joka identifioidaan ID:llä
        News n = newsRepository.getOne(editId);
        n.setContent(news.getContent());
        n.setIngress(news.getIngress());
        n.setHeader(news.getHeader());
        n.setWriters(news.getWriters());
        n.setCategories(news.getCategories());
        newsRepository.save(n);

        return "redirect:/";
    }

    @PostMapping("/addNews")
    public String addNews(@Valid @ModelAttribute News news, BindingResult bindingResult,
                          Model model, @RequestParam("newsObject")
                                  MultipartFile newsObject) throws IOException {

        if (!securityService.checkCredentials()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            List<String> e = newsValidatorService.getErrorMessages(bindingResult);
            model.addAttribute("errors", e);
            return "addNews";
        }


        NewsObject no = new NewsObject();
        no.setContent(newsObject.getBytes());
        no.setNews(news);
        news.setClicks(0);
        news.setPublishdate(LocalDate.now());

        newsRepository.save(news);
        newsObjectRepository.save(no);

        return "redirect:/";
    }


    @Transactional
    @GetMapping(path = "/image/{id}/", produces = {"image/png", "image/jpg"})
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        News n = newsRepository.getOne(id);
        return newsObjectRepository.findByNews(n).getContent();

    }


    @Transactional
    public void addAsideListNewsAndNavbar(Model model) {
        model.addAttribute("navbarCategories", categoryRepository.findByPinned(new Boolean(true)));
        model.addAttribute("newest", newsFinderService.findNewest());
        model.addAttribute("mostPopular", newsFinderService.findMostPopular());
    }


}

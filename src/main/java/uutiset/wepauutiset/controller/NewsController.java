package uutiset.wepauutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uutiset.wepauutiset.domain.*;
import uutiset.wepauutiset.repository.*;
import uutiset.wepauutiset.service.NewsFinderService;
import uutiset.wepauutiset.service.ValidatorService;
import uutiset.wepauutiset.service.SecurityService;

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
    private ValidatorService validatorService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private NewsClickRepository newsClickRepository;

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
        NewsClick nc = new NewsClick();
        nc.setNews(news);
        nc.setClickdate(LocalDate.now());

        newsClickRepository.save(nc);
        news.addLick(nc);
        newsRepository.save(news);

        model.addAttribute("n", news);
        addWritersAndCategories(model);
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
        addWritersAndCategories(model);
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
    public String addNew(@ModelAttribute News news, Model model) {
        addAsideListNewsAndNavbar(model);
        addWritersAndCategories(model);

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

        if (!securityService.checkCredentials()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            news.setEditId(editId);
            rel.addFlashAttribute("errors", validatorService.getErrorMessages(bindingResult));
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

        rel.addFlashAttribute("messages", "Newsstory saved with edited information!");
        return "redirect:/";
    }

    @PostMapping("/addNews")
    public String addNews(@Valid @ModelAttribute News news, BindingResult bindingResult,
                          Model model, RedirectAttributes rel, @RequestParam("newsObject")
                                  MultipartFile newsObject) throws IOException {

        if (!securityService.checkCredentials()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            List<String> e = validatorService.getErrorMessages(bindingResult);
            model.addAttribute("errors", e);
            addAsideListNewsAndNavbar(model);
            addWritersAndCategories(model);
            return "addNews";
        }


        NewsObject no = new NewsObject();
        no.setContent(newsObject.getBytes());
        no.setNews(news);
        news.setClicks(new ArrayList<>());
        news.setPublishdate(LocalDate.now());

        newsRepository.save(news);
        newsObjectRepository.save(no);

        rel.addFlashAttribute("messages", "Newsstory added!");

        return "redirect:/";
    }

    @DeleteMapping("news/delete/{id}")
    public String deleteOne(@PathVariable Long id, RedirectAttributes rel) {
        if (!securityService.checkCredentials()) {
            return "index";
        }
//        News news = this.newsRepository.getOne(id);
//        List<NewsClick> nc = newsClickRepository.findAllByNews(news);
//        for (NewsClick n : nc) {
//            newsRepository.deleteById(n.getNews().getId());
//        }
        this.newsRepository.deleteById(id);


        rel.addFlashAttribute("message", "Newsstory deleted!");
        return "redirect:/";


    }


    @Transactional
    @GetMapping(path = "/image/{id}/", produces = {"image/png", "image/jpg"})
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        News n = newsRepository.getOne(id);
        return newsObjectRepository.findByNews(n).getContent();

    }

    public void addAsideListNewsAndNavbar(Model model) {
        model.addAttribute("navbarCategories", categoryRepository.findByPinned(new Boolean(true)));
        model.addAttribute("newest", newsFinderService.findNewest());
        model.addAttribute("mostPopular", newsFinderService.findMostPopular(newsRepository.findAll()));
    }

    private void addWritersAndCategories(Model model) {
        model.addAttribute("writers", newsWriterRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
    }


}
